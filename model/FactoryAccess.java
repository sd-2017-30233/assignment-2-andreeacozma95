package model;

public class FactoryAccess<T> {
	Class<T> class1;

	public FactoryAccess(Class<T> class1){
		this.class1 = class1;
	}
    public T getFactory(){
    	try {
			return class1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
  
    	 return null; 
    }
	
	public static void main(String args[])
	{
		FactoryAccess<PDFFactory> factory = new FactoryAccess<>(PDFFactory.class);
		Factory c = factory.getFactory();
		System.out.println(c.getClass().getName());
	}
	
	
}
