# Climbing Session Tracker

A Java command-line application for logging indoor climbing sessions, tracking grade distributions, and maintaining persistent climbing history using text files.

## Features
- Log individual climbing sessions with date, climb name, grade, attempts, and send status
- Defensive input validation for grades, attempts, and yes/no responses
- Persistent storage of climbing history and grade distribution using text files
- Automatic calculation of total climbs and average grade
- Utility to reset stored data to an initial clean state

## How to Run
1. Compile the Java files: javac *.java
2. Run the session tracker: java Session
3. (Optional) Reset all stored climbing data: java RestartTextFiles

## File Overview
- `Session.java` – Main application logic, user input handling, and file updates
- `RestartTextFiles.java` – Utility class to reset climbing history and grade distribution files
- `ClimbingHistory.txt` – Stores a persistent record of all logged climbing sessions
- `GradeDistribution.txt` – Stores cumulative grade distribution and statistics
