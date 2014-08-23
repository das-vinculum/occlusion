package org.knittingpatterndesigner.incubator.occlusion;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 22.08.14
 */
public class Occlusion {

    public Occlusion(Display display) {
        Shell shell = new Shell(display);
        shell.setText("Occlusion");
        shell.setSize(250, 200);

        center(shell);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    public void center(Shell shell) {
        Rectangle bds = shell.getDisplay().getBounds();

        Point p = shell.getSize();

        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;

        shell.setBounds(nLeft, nTop, p.x, p.y);
    }

    public static void main(String[] args) {
        Display display = new Display();
        new Occlusion(display);
        display.dispose();
    }
}
