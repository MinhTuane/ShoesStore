package shoesStore.model.user;

import java.util.List;

import shoesStore.model.Product;

public class User {
		private int id;
		private String username;
		private String password;
		private List<Product> cart;
		private List<Product> liked;
		private String role;
		
		public User(int id, String username, String password, String role) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public List<Product> getCart() {
			return cart;
		}
		public void setCart(List<Product> cart) {
			this.cart = cart;
		}
		public List<Product> getLiked() {
			return liked;
		}
		public void setLiked(List<Product> liked) {
			this.liked = liked;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
}
