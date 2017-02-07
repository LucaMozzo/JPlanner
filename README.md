# JPlanner
JPlanner is an Object-Oriented AI planner implemented in Java. Although it's inspired from PDDL, it works 100% in Java: problems and domains can be dynamically created in Java.

## Versions

### Branch master (v1.0)
![Build Status](https://img.shields.io/badge/build-passed-green.svg?style=flat-square)
Working version with basic features of PDDL. It doesn't support objects and parameterized actions.

### Branch newv (v2.0)
![Build Status](https://img.shields.io/badge/build-failed-red.svg?style=flat-square)
![Build Status](https://img.shields.io/badge/test-failed-red.svg?style=flat-square)
Full support for PDDL 2.x, object-oriented and added support for parameterized actions. Still uses uninformed search, heuristics will be introduced from next version. Currently under development, unit testing used from this version.

### Features coming out in the future
- Temporal planning
- Heuristics and informed search
- Advanced metric that allows you to choose the solution (optimal, optimize variable, etc)
- External factors/environmental processes
- Support for multithreading and multiprocessing
