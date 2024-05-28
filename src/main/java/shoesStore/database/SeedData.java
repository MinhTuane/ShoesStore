package shoesStore.database;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import shoesStore.DAO.HeaderDAO;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.DateUtils;
import shoesStore.model.HeaderImage;
import shoesStore.model.Product;

public class SeedData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getAll();
		if(products.size()==0) {
			String directoryPath = "C:\\Study\\WorkSpace eclipse\\CSE 456\\ShoesStore\\src\\main\\webapp\\view\\images"; // Change this to your directory

	        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
	            paths.filter(Files::isRegularFile) // Only include regular files
	                 .forEach(path -> {
	                	 String name = removeExtension(path.getFileName().toString());
	                	 
	                	 double cost = roundToTwo(100 + (1000-100) * new Random().nextDouble());
	                	 
	                	 byte [] image = productDAO.readImageAsByteArray(path.toString());
	                	 
	                	 LocalDate startDate = LocalDate.of(2020, 1, 1);
	                     LocalDate endDate = LocalDate.of(2024, 1, 1);
	                     Date date = DateUtils.randomSqlDate(startDate, endDate);
	                     long sold = (long) (Math.random() * 1001);
	                     double rate = roundToTwo(3 + 2* new Random().nextDouble());
	                     
	                     Product product = new Product(0,name,cost,image,date,sold,rate);
	                     
	                     products.add(product);
	                     productDAO.insert(product);
	                 });
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		HeaderDAO headerDao = new HeaderDAO();
		List<HeaderImage> headerImages = headerDao.getAll();
		if(headerImages.size()==0) {
			String directoryPath = "C:\\Study\\WorkSpace eclipse\\CSE 456\\ShoesStore\\src\\main\\webapp\\view\\images\\header"; // Change this to your directory

	        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
	            paths.filter(Files::isRegularFile) // Only include regular files
	                 .forEach(path -> {
	                	 String name = removeExtension(path.getFileName().toString());
	                	 
	                	 
	                	 byte [] image = productDAO.readImageAsByteArray(path.toString());
	                     
	                     HeaderImage headerImage = new HeaderImage(0,name,image);
	                     
	                     headerImages.add(headerImage);
	                     headerDao.insert(headerImage);
	                 });
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
	}

	private static String removeExtension(String fileName) {
		int lastDotIndex = fileName.lastIndexOf('.');
		String regex = "[-_]";
		if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
			return fileName.substring(0, lastDotIndex).replaceAll(regex, " ");
		}
		return fileName.replaceAll(regex, " ");
	}
	
	public static double roundToTwo(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
