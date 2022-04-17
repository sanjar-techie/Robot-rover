# Repository for Rover - robotics project
A simple robot design (inspired by Mars Rover) that can interact with its environment by using behaviour-based robotics to avoid obstacles, detect colored regions, and localize (keep track of its location and travel to its destionation).

## Prerequisites

You will need LeJOS, which is a Java runtime and VM for Lego Mindstorms, along with a Java IDE of your choosing with the toolchain needed to build and send the compiled bytecode to the Mindstorms unit.

```
cp -R repository-template-java/ new-name && cd new-name && git config --local --unset remote.origin.url && git config --local --add remote.origin.url git@github.com:baloise/new-name.git && git reset --hard $(git commit-tree FETCH_HEAD^{tree} -m "Initial contribution") &&  git grep -l 'repository-template-java' | xargs sed -i '' -e 's/repository-template-java/new-name/g' && mvn clean verify && git add -A && git commit -m "Rename from template to new-name" && cd ..
```


## Hardware
- A Lego Mindstorms differential drive robot, with the following sensors:
    - Colour sensor, facing down;
    - Sonar sensor, facing the front, for distance detection.
 
![rover-image](https://user-images.githubusercontent.com/63178523/163710948-11988101-8630-400d-9630-e76169c56942.jpg)

## Architecture
We will be using the subsumption method as is represented below:

![rover](https://user-images.githubusercontent.com/63178523/163710869-094923de-f8ba-41d0-8d8d-5499ea60a2ff.png)

