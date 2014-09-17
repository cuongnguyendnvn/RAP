package org.akrogen.dynaresume.raphelloworld.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public enum TableModelProvider {INSTANCE;
	
	private List<TableModel> listTableModel;
	private EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	private TableModelProvider() {
		/*listTableModel = new ArrayList<TableModel>();
		
		listTableModel.add(new TableModel("Item0-0", "Item0-1", "Item0-2", "Item0-3", "Item0-4", "Item0-5", "Item0-6", "Item0-7", "Item0-8", "Item0-9"));
		listTableModel.add(new TableModel("Item1-0", "Item1-1", "Item1-2", "Item1-3", "Item1-4", "Item1-5", "Item1-6", "Item1-7", "Item1-8", "Item1-9"));
		listTableModel.add(new TableModel("Item2-0", "Item2-1", "Item2-2", "Item2-3", "Item2-4", "Item2-5", "Item2-6", "Item2-7", "Item2-8", "Item2-9"));*/
		
		emf = Persistence.createEntityManagerFactory("demo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select t from TableModel t");
		listTableModel = query.getResultList();
		
		em.close();
	}
	
	public List<TableModel> getListTable() {
		return listTableModel;
	}
}
