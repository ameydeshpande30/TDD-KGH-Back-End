package tdd.kgh.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tdd.kgh.file.FileStorageService;
import tdd.kgh.file.UploadFileResponse;
import tdd.kgh.models.jdbc.Customer;
import tdd.kgh.operations.CustomerOperations;

@RestController
@RequestMapping("/api/customer")
public class CustomerManagement {
	private static final Logger logger = LoggerFactory.getLogger(CustomerManagement.class);

    @Autowired
    private FileStorageService fileStorageService;
    
	@Autowired
	CustomerOperations CustomerOperations ;
	@GetMapping("/getList")
	public ArrayList<Customer> findAll() {
		try {
			
			return CustomerOperations.showCustomerList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/del/{id}")
	public boolean delRoom(@PathVariable int id) {
		try {
			return CustomerOperations.deleteCustomer(new Customer(id, "name", "address", "7588758032", "ameyd30@gmail.com", "aadhar",
					"idproof"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@PostMapping("/addUpdate")
	public String addUpdate(@RequestBody Map<String, Object> payload, @RequestParam("file") MultipartFile file )  {
		
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		String name = (String) payload.get("name");
		String address = (String) payload.get("address");
		String contactNumber = (String) payload.get("contactNumber");
		String email = (String) payload.get("email");
		String aadhar = (String) payload.get("aadhar");
//		String idproof = (String) payload.get("idproof");
		String fileName = fileStorageService.storeFile(file);
		String idproof = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/customer/downloadFile/")
                .path(fileName)
                .toUriString();
		
		if(update == 1) {
			try {
				CustomerOperations.updateCustomer(new Customer(id, name, address, contactNumber, email, aadhar, idproof));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				CustomerOperations.addCustomer(new Customer(id, name, address, contactNumber, email, aadhar, idproof));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	 @PostMapping("/uploadFile")
	    public String uploadFile(@RequestParam("file") MultipartFile file) {
	        String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/customer/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        ArrayList<UploadFileResponse> al = new ArrayList<>();
	        al.add(new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize()));
	        return fileDownloadUri;
	    }
	 @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
}
