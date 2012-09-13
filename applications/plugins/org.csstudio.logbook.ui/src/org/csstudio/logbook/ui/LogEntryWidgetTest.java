/**
 * 
 */
package org.csstudio.logbook.ui;

import org.csstudio.logbook.LogEntry;
import org.csstudio.logbook.LogEntryBuilder;
import org.csstudio.logbook.LogbookBuilder;
import org.csstudio.ui.util.widgets.RangeWidgetTest;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

/**
 * @author shroffk
 * 
 */
public class LogEntryWidgetTest extends ApplicationWindow {

	public LogEntryWidgetTest() {
		super(null);
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(5, false));
		final LogEntryWidget logEntryWidget = new LogEntryWidget(container,
				SWT.WRAP);
		logEntryWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 5, 1));

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LogEntry logEntry = LogEntryBuilder.withText("SomeText\nsome more text")
						.owner("shroffk")
						.addLogbook(LogbookBuilder.logbook("test"))
						.addLogbook(LogbookBuilder.logbook("test2"))
						.addLogbook(LogbookBuilder.logbook("test3"))
						.addLogbook(LogbookBuilder.logbook("test4"))
						.addLogbook(LogbookBuilder.logbook("test5")).build();
				logEntryWidget.setLogEntry(logEntry);
			}
		});
		btnNewButton.setText("test logEntry");

		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LogEntry logEntry = LogEntryBuilder.withText("SomeText")
						.owner("shroffk")
						.addLogbook(LogbookBuilder.logbook("test"))
						.addLogbook(LogbookBuilder.logbook("test2")).build();
				logEntryWidget.setLogEntry(logEntry);
			}
		});
		btnNewButton_1.setText("simple Entry");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		return container;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			LogEntryWidgetTest window = new LogEntryWidgetTest();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(473, 541);
	}

}
