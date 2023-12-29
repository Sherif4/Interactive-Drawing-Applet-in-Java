                                                    Java Applet Documentation
Introduction
The Java applet is a simple drawing application that allows users to create and manipulate various shapes using different tools such as rectangles, ovals, lines, brushes, erasers, and more. The applet provides a user interface with buttons and checkboxes to select different drawing modes, colors, and options.
Features
1.	Drawing Modes:
    o	Rectangle
    o	Oval
    o	Line
    o	Brush
    o	Eraser
2.	Color Selection:
    o	Red
    o	Green
    o	Blue
3.	Options:
    o	Checkbox for Solid Fill
    o	Buttons for Clear All, Undo, and Redo
User Interface
The applet's user interface consists of several buttons, checkboxes, and a drawing area. Users can select a drawing mode and color, and they can perform actions like clearing the canvas, undoing/redoing actions, and choosing between solid or outlined shapes.
Buttons:
    •	Rectangle: Selects the rectangle drawing mode.
    •	Oval: Selects the oval drawing mode.
    •	Line: Selects the line drawing mode.
    •	Brush: Selects the brush drawing mode.
    •	Eraser: Selects the eraser mode.
    •	Clear All: Clears the entire canvas.
    •	Undo: Undoes the last drawing action.
    •	Redo: Redoes the last undone action.
    •	Red: Selects red as the drawing color.
    •	Green: Selects green as the drawing color.
    •	Blue: Selects blue as the drawing color.
Checkbox:
    •	Solid: Toggles between solid and outlined shapes.
Drawing Area
    The drawing area displays the shapes drawn by the user. It updates in real-time as the user interacts with the applet.
    Implementation Details
Classes:
1.	Project (Applet Class):
    o	Handles the applet lifecycle and user interface.
    o	Manages button actions, color selection, drawing modes, and user input.
2.	MyMouseListener (Inner Class):
    o	Listens to mouse events for handling drawing actions.
    o	Differentiates between mouse movements, drags, presses, and releases to determine the drawing behavior.
3.	Shapes (Abstract Class):
    o	Base class for different shapes.
    o	Defines common attributes and abstract method draw for drawing shapes.
4.	Concrete Shape Classes (Rectangle, Oval, Line, Brush, Eraser, Cursor):
    o	Inherit from the abstract Shapes class.
    o	Implement the draw method with specific drawing logic for each shape.
Usage
1.	Selecting Drawing Mode:
    o	Click the respective buttons (Rectangle, Oval, Line, Brush, Eraser) to choose the desired drawing mode.
2.	Choosing Color:
    o	Click Red, Green, or Blue buttons to select the drawing color.
3.	Solid Fill:
    o	Check the "Solid" checkbox for solid fill (applies to applicable shapes).
4.	Drawing:
    o	Use the mouse to draw shapes based on the selected mode.
5.	Clearing Canvas:
    o	Click the "Clear All" button to clear the entire canvas.
6.	Undo/Redo:
    o	Use the "Undo" and "Redo" buttons to reverse or redo drawing actions.


Notes
    •	The application supports various shapes and drawing tools, providing a versatile drawing experience.
    •	Users can experiment with different colors, drawing modes, and options to create diverse drawings.
    •	The applet maintains an undo/redo history to facilitate easy correction of drawing actions.
Conclusion
    The Java applet is a lightweight drawing application that offers a range of features for users to express their creativity through     digital drawing. With its simple interface and diverse drawing tools, it provides an engaging platform for artistic expression.

