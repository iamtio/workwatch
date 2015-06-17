# Work watch
Work watch is a app for measuring your working time.
App starts counting time elapsed from PC started and shows it in system tray.
Also it can show time remaining for end of working day.

# Features:
- Showing computer uptime in tray
- Showing work remaining time in tray
- Showing current time in tray
- Customizable colors and font size
- Customizable working hours

# Screenshots
Tray icon:

![ww1](https://cloud.githubusercontent.com/assets/597141/8105614/52dee40a-1043-11e5-81ce-6cc97fa04371.png)

Settings:

![ww2](https://cloud.githubusercontent.com/assets/597141/8105652/a353a98e-1043-11e5-917e-8bb09d751bd6.png)

![ww3](https://cloud.githubusercontent.com/assets/597141/8105654/ac43396a-1043-11e5-8507-fa544c5cca3d.png)

# How to
To build application you need to install:
- JDK 1.7 or higher (openjdk is fine)
- Apache Maven 3.0 or higher

## Build

    git clone git@github.com:iamtio/workwatch.git
    cd workwatch
    mvn package
    
## Run

    java -jar target/workwatch-*.jar &
