# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Tree of Life is a Kotlin/Java desktop application that visualizes life events on a monthly timeline. The application reads a structured text file containing life events and displays them graphically as a timeline with different categories (homes, education, relationships, etc.).

## Architecture

### Core Components

- **Main Application (`src/Main.kt`)**: Java Swing desktop application with file watching capabilities
- **Data Layer (`src/Data/`)**: Parsing and data structures for timeline events
- **Visualization Layer (`src/Visualization/`)**: UI components for rendering the timeline
- **Test Suite (`tests/`)**: Unit tests for all major components

### Key Data Structures

- `TimePoint`: Represents a specific month/year combination
- `Period`: Represents a time span with start/end points and a name
- `Category`: Groups related periods (e.g., "Homes", "Education")
- `TreeOfLifeData`: Main data container with birth month, categories, and name

### Data File Format

The application reads from `~/Documents/TreeOfLife.txt` with this structure:
```
Birth month: Jul 1979
Name: Person Name

---Category Name---
Event Name: Mon YYYY-Mon YYYY
Another Event: Jan-Dec 2020

###

---Another Category---
...
```

## Development Commands

### Build and Run
```bash
./gradlew build
./gradlew run
```

### Testing
```bash
./gradlew test
```

### File Structure
- `src/`: Main source code
  - `Data/`: Data models and parsing logic
  - `Visualization/`: UI components
- `tests/`: Test files
- `build.gradle.kts`: Gradle build configuration

## Key Features

- **File Watching**: Automatically reloads when the data file changes
- **Interactive Timeline**: Mouse cursor shows current position and overlapping events
- **Multiple Categories**: Different life aspects displayed as separate tracks
- **Real-time Updates**: No need to restart when editing the data file
- **PNG Export**: Export timeline visualization to PNG (8000x2400 pixels wide format) via File menu

## Testing Approach

The project uses JUnit for testing with comprehensive coverage of:
- Data parsing logic
- Time calculations
- UI component behavior
- File format validation

Run individual test classes:
```bash
./gradlew test --tests "CategoryTests"
```

## Development Notes

- The application creates a default data file if none exists
- File watching checks for changes every second
- UI uses Java Swing with custom painting for timeline visualization
- Color scheme automatically generates contrasting colors for categories