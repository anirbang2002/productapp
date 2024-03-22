package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model m) {
		List<Product> products = productDao.getProducts();
		m.addAttribute("products",products);
		
		return "home";
	}
	
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		
		m.addAttribute("title", "Add Product");
		return "add_product_form";
	}
	
	@RequestMapping(value="/handle-product", method = RequestMethod.POST)
	public RedirectView handleproduct(@ModelAttribute Product product,HttpServletRequest request) {
		System.out.println(product);
		productDao.createproduct(product);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	
	@RequestMapping("/delete/{productId}")
	 public RedirectView deleteproduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		 System.out.println(productId);
			productDao.deleteProduct(productId);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/");
			return redirectView;
	 }
	
	@RequestMapping("/update/{productId}")
	public String updateproduct(@PathVariable("productId") int productId, Model m) {
		Product product = productDao.getProduct(productId);
		m.addAttribute("product",product);
		return "update_product_form";
	}
	
	
}
/*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*/
