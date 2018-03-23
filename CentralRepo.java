import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* Pseudo central repository. */
public class CentralRepo
{
    private HashMap<String, ArrayList<String>> dependencyList;                      // list of package dependencies in repo
    
    public CentralRepo()
    {
	this.dependencyList = new HashMap<String, ArrayList<String>>();
	buildDependencyList();
    }
   
    /* Build list of package dependencies for look up. */
    private void buildDependencyList()
    {
	this.dependencyList.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
	this.dependencyList.put("B", new ArrayList<String>());
	this.dependencyList.put("C", new ArrayList<String>());
	this.dependencyList.put("D", new ArrayList<String>(Arrays.asList("E", "F", "G")));
	this.dependencyList.put("E", new ArrayList<String>());
	this.dependencyList.put("F", new ArrayList<String>(Arrays.asList("C")));
	this.dependencyList.put("G", new ArrayList<String>());
    }

    /* Dependency list getter. */
    public HashMap<String, ArrayList<String>> getDependencyList()
    {
	return this.dependencyList;
    }
}
