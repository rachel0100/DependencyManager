# DependencyManager
Pseudo dependency manager to demonstrate an implementation of topological sort with depth first search (DFS).  
  
## Problem Statement
Prototype a dependency management system. Assume you have an API method that queries a central repository for each package’s dependencies. Dependencies of packages may themselves have dependencies, and packages may share dependencies through arbitrary chains of dependencies. For example, if A depends on B & C, and D depends on E & F & G, and F depends on C, then A and D implicitly share the dependency on C. You may assume there is a pre-existing function for downloading and installing a given package which succeeds only if all of its dependencies are already installed. You can ignore package versioning (i.e., you can assume each package only has one version) and assume there are no circular dependencies. A good solution will avoid installing shared dependencies multiple times, and cache package installations if the package was previously installed through the dependency manager.  

## Code Description
### CentralRepo.java
Acts as the pseudo central repository containing all available packages and their dependencies.  
### PackageManager.java
Implements the proof of concept of installing and deleting packages using the topological sort with depth first search algorithm.  
