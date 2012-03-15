package org.csstudio.channel.views;

import gov.bnl.channelfinder.api.ChannelQuery;
import gov.bnl.channelfinder.api.ChannelQuery.Result;
import gov.bnl.channelfinder.api.ChannelQueryListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.csstudio.channel.widgets.ChannelQueryInputBar;
import org.csstudio.channel.widgets.ChannelTreeByPropertyWidget;
import org.csstudio.channel.widgets.PopupMenuUtil;
import org.csstudio.utility.pvmanager.ui.SWTUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * View that allows to create a tree view out of the results of a channel query.
 */
public class ChannelTreeByPropertyView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.csstudio.channel.views.ChannelTreeByPropertyView";

	/** Memento */
	private IMemento memento = null;
	
	private final ChannelQueryListener channelQueryListener = new ChannelQueryListener() {
		
		@Override
		public void queryExecuted(final Result result) {
			SWTUtil.swtThread().execute(new Runnable() {
				
				@Override
				public void run() {
					btnProperties.setEnabled(result.channels != null && !result.channels.isEmpty());
				}
			});
		}
	};
	
	/**
	 * The constructor.
	 */
	public ChannelTreeByPropertyView() {
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	@Override
	public void init(final IViewSite site, final IMemento memento)
			throws PartInitException {
		super.init(site, memento);
		// Save the memento
		this.memento = memento;
	}

	@Override
	public void saveState(final IMemento memento) {
		super.saveState(memento);
		treeWidget.saveState(memento);
	}
	
	private ChannelQueryInputBar inputBar;
	private ChannelTreeByPropertyWidget treeWidget;
	private Button btnProperties;
	
	public void setChannelQuery(ChannelQuery query) {
		inputBar.setChannelQuery(query);
		ChannelQuery oldQuery = treeWidget.getChannelQuery();
		if (oldQuery != null) {
			oldQuery.removeChannelQueryListener(channelQueryListener);
		}
		if (query != null) {
			query.execute(channelQueryListener);
		}
		treeWidget.setChannelQuery(query);
	}
	
	public void configure() {
		treeWidget.openConfigurationDialog();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		Label lblPvName = new Label(parent, SWT.NONE);
		FormData fd_lblPvName = new FormData();
		fd_lblPvName.left = new FormAttachment(0, 5);
		fd_lblPvName.top = new FormAttachment(0, 8);
		lblPvName.setLayoutData(fd_lblPvName);
		lblPvName.setText("Query:");
		
		inputBar = new ChannelQueryInputBar(parent, SWT.NONE,
				Activator.getDefault().getDialogSettings(), "channeltreebypropertyview.query");
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(0, 5);
		fd_combo.left = new FormAttachment(lblPvName, 6);
		inputBar.setLayoutData(fd_combo);
		inputBar.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if ("channelQuery".equals(event.getPropertyName())) {
					setChannelQuery((ChannelQuery) event.getNewValue());
				}
			}
		});
		
		treeWidget = new ChannelTreeByPropertyWidget(parent, SWT.NONE);
		FormData fd_waterfallComposite = new FormData();
		fd_waterfallComposite.top = new FormAttachment(inputBar, 6);
		fd_waterfallComposite.bottom = new FormAttachment(100, -5);
		fd_waterfallComposite.left = new FormAttachment(0, 5);
		fd_waterfallComposite.right = new FormAttachment(100, -5);
		treeWidget.setLayoutData(fd_waterfallComposite);
		
		btnProperties = new Button(parent, SWT.NONE);
		fd_combo.right = new FormAttachment(btnProperties, -6);
		FormData fd_btnProperties = new FormData();
		fd_btnProperties.top = new FormAttachment(0, 5);
		fd_btnProperties.right = new FormAttachment(100, -5);
		btnProperties.setLayoutData(fd_btnProperties);
		btnProperties.setText("Properties");
		btnProperties.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				configure();
			}
		});
		btnProperties.setEnabled(false);
		
		treeWidget.loadState(memento);
		inputBar.setChannelQuery(treeWidget.getChannelQuery());
		
		PopupMenuUtil.installPopupForView(treeWidget, getSite(), treeWidget);
		PopupMenuUtil.installPopupForView(inputBar, getSite(), inputBar);
	}
}