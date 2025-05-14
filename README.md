# CalcMate - Java Swing Calculator

![CalcMate Calculator](https://github.com/user-attachments/assets/fd0c55b1-8653-41de-92fd-8414873fafcc)


## Overview

CalcMate is a visually appealing, functional calculator application built with Java Swing. This project demonstrates fundamental concepts of GUI development in Java while providing a practical utility for everyday calculations.

## Features

- **Clean, Modern Interface**: Features a pleasant blue-themed design with carefully selected color palettes
- **Basic Arithmetic Operations**: Addition, subtraction, multiplication, and division
- **Additional Functions**: 
  - Percentage calculations
  - Sign toggle (+/-)
  - Decimal point input
  - Backspace functionality
- **Calculation History**: View your previous calculations in a scrollable history panel
- **Error Handling**: Gracefully handles division by zero and other calculation errors

## Screenshots

![Calculator Interface](https://github.com/user-attachments/assets/6410af10-a87a-4fb4-8f8e-edfd6190bc82)

![History Panel](https://github.com/user-attachments/assets/bf518b6d-87c6-4d47-871c-0b9978265229)


## Technical Details

- **Programming Language**: Java
- **GUI Framework**: Java Swing
- **Layout Management**: BorderLayout and GridLayout for responsive design
- **Event Handling**: Lambda expressions for clean, modern action listeners

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system
2. Compile the source code:
   ```bash
   javac Calculator.java
   ```
3. Run the application:
   ```bash
   java Calculator
   ```

## Usage Guide

- **Basic Operations**: Enter the first number, select an operation, enter the second number, and press equals
- **Continuous Calculations**: Results automatically become the first operand for the next operation
- **Special Functions**:
  - **C**: Clear all current input and operations
  - **±**: Toggle between positive and negative values
  - **%**: Convert the current number to a percentage (divide by 100)
  - **←**: Delete the last digit entered

## Design Highlights

- **Color Scheme**:
  - Background: Deep blue tones (RGB: 33, 52, 72)
  - Display: Elegant yellow text (RGB: 236, 239, 202) on dark blue background
  - Buttons: Subdued gray with vibrant blue text
- **Typography**: Clear, readable fonts with appropriately sized text
- **Layout**: Intuitive button arrangement with proper spacing and alignment

## Future Enhancements

- Scientific calculator functions
- Memory functions (M+, M-, MR, MC)
- Customizable themes
- Keyboard input support
- Expression evaluation feature

## About the Author

This project was developed by **Ankesh Kumar** as a fun project to demonstrate Java GUI programming skills and create a practical everyday tool.

---

© 2025 Ankesh Kumar. All rights reserved.
