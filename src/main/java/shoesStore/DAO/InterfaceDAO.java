package shoesStore.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
	
	public List<T> getAll();
	
	public T detail(int id);
	
	public boolean insert(T t);
	
	public boolean edit(T t);
	
	public boolean delete(int id);
}
