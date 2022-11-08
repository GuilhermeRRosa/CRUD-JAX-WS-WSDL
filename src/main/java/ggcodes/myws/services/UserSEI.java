package ggcodes.myws.services;

import ggcodes.myws.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface UserSEI {

	@WebMethod
	public long save(User u);
	
	@WebMethod
	public boolean delete(long id);
	
}
