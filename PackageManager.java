import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PackageManager
{
    private CentralRepo centralRepo;
	private HashMap<String, ArrayList<String>> centralRepoDepList;
	private Stack<String> stack;
	private ArrayList<String> cache;
    
    public PackageManager()
    {
	this.centralRepo = new CentralRepo();
	this.centralRepoDepList = centralRepo.getDependencyList();
	this.stack = new Stack<String>();
	this.cache = new ArrayList<String>();
    }

	/* Check is package is available in repo. */
	private boolean isAvailable(String packageName) 
	{
		if (this.centralRepoDepList.containsKey(packageName)) return true;
		System.out.println(packageName + " is not available in the central repository.");
		System.out.println();
		return false;
	}

	/* Check is package is installed. */
	private boolean isInstalled(String packageName) 
	{
		if ((this.stack.contains(packageName)) || (this.cache.contains(packageName))) {
			System.out.println(packageName + " is already installed.");
			System.out.println();
			return true;
		}
		return false;
	}
	
	/* Adds package. */
	public void addPackage(String packageName) 
	{
		if ((!isInstalled(packageName)) && (isAvailable(packageName))) {					      // if uninstalled and available
			ArrayList<String> dependencies = this.centralRepoDepList.get(packageName);		// get package dependencies
			for (String u : dependencies) {										// for each dependency child
				topologicalOrder(u, true);
			}
			this.cache.add(packageName);											// cache parent package
			this.stack.push(packageName);											// push parent package to stack
			installPackage();																  // install packages on the stack
			this.stack.clear();																// clear the stack
		}
	}

	/* Deletes package. */
	public void deletePackage(String packageName)
	{
		if(isInstalled(packageName)) {
			ArrayList<String> dependencies = this.centralRepoDepList.get(packageName);
			for (String u : dependencies) {
				topologicalOrder(u, false);
			}
		}
		this.cache.remove(packageName);
		this.stack.clear();
	}

	/* Recursive depth first search based topological sorting. */
	public void topologicalOrder(String packageName, boolean addToCache) 
	{
		ArrayList<String> dependencies = this.centralRepoDepList.get(packageName);
		for (String u : dependencies) {
			if (!this.stack.contains(u)) topologicalOrder(u, addToCache);	
		}
		stack.push(packageName);
		if (addToCache) {
			this.cache.add(packageName);											// if installing packages, cache
		} else {
			this.cache.remove(packageName);										// if uninstalling packages, remove from cache
		}
	}

	/* "API" to call "central repo" to install package. */
	private void installPackage() 
	{
		for ( String u : this.stack ) {
			System.out.println("API call to install package: " + u);
		}
	}
    
    public static void main(String[] args)
    {
	PackageManager manager = new PackageManager();

	manager.addPackage("A"); 
	manager.addPackage("D");
	manager.addPackage("D");                              // will not reinstall
	manager.addPackage("H");                              // not found in repo, will not install
	System.out.println("Installed packages: " + manager.cache);
	manager.deletePackage("D");                           // will not uninstall any shared dependencies with A
	System.out.println("Installed packages: " + manager.cache);
    }
}
