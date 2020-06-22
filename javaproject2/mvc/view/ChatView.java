package mvc.view;

import java.util.List;
import javax.swing.*;

import mvc.controller.ChatController;
import mvc.model.ChatModel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import observer.ChatObserver;
import observer.Observable;

public class ChatView implements ChatObserver, Observable {

	private List<ChatObserver> chatObserversList= new ArrayList<>();
	 private ChatModel model;
	    private JScrollPane scroll;
	    private Container container;
	    private JButton submit;
	    private ButtonGroup group;
	    private JRadioButton firstGui;
	    private JRadioButton secondGui;
	    private JTextArea textArea;
	    private JPanel panel;
	    private JTextField textField;
	    private JFrame frame;
	    private static int counter = 0;
	    private int id;
	public ChatView(ChatModel model) {
			super();
			this.model = model;
			id = ++counter;
	        frame = new JFrame("Client " + id);
	        scroll = new JScrollPane();
	        container = frame.getContentPane();
	        textArea = new JTextArea("", 10, 40);
	        panel = new JPanel();
	        textField = new JTextField(20);
	        submit = new JButton("Submit");
	        group = new ButtonGroup();
	        firstGui = new JRadioButton("FirstGui", true);
	        secondGui = new JRadioButton("SecondGui");
	        container.setLayout(new BorderLayout());
	        secondGui.addItemListener(selectionHandler);
	        submit.addActionListener(submitHandler);
	        model.attach(this);
	        this.setup1();
	}

	@Override
	public void attach(ChatObserver forumObserver) {
		chatObserversList.add(forumObserver);

	}

	@Override
	public void detach(ChatObserver forumObserver) {
		chatObserversList.remove(forumObserver);

	}

	@Override
	public void inform() {
		List<ChatObserver> observerList = forumObservers();
		while (observerList.iterator().hasNext()) {
			observerList.iterator().next().update();
		}
	}
	@Override
	public void update() {
		 textArea.setText(model.getMessages());
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	public List<ChatObserver> forumObservers() {

		List<ChatObserver> observersList = new ArrayList<>();
		for (ChatObserver copy :chatObserversList) {
			try {
				observersList.add((ChatObserver) copy.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return observersList;
	}
	


    public void setup1() {
        scroll.setViewportView(textArea);
        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
        group.add(firstGui);
        group.add(secondGui);
        panel.add(firstGui);
        panel.add(secondGui);
        panel.add(textField);
        panel.add(submit);
        frame.pack();
        frame.setVisible(true);
    }

    public void setup2() {
        scroll.setViewportView(textArea);
        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.NORTH);
        group.add(firstGui);
        group.add(secondGui);
        panel.add(firstGui);
        panel.add(secondGui);
        panel.add(textField);
        panel.add(submit);
        frame.pack();
        frame.setVisible(true);
    }

    public void showDisplay(int display) {
            String text = model.getMessages();
        if (display == ItemEvent.SELECTED) {
            frame.setVisible(false);
            textArea.setText(text);
            setup2();
        }
        else {
            frame.setVisible(false);
            textArea.setText(text);
            setup1();
        }
    }

    ItemListener selectionHandler = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            ChatController con;
            for (int i = 0; i < chatObserversList.size(); i++) {
                con = (ChatController)chatObserversList.get(i);
                  con.processSelectionAction(e.getStateChange());
            }
        }
    };

    ActionListener submitHandler = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            inform();
            ChatController con;
            for (int i = 0; i < chatObserversList.size(); i++) {
                con = (ChatController)chatObserversList.get(i);
                con.processSubmitAction("Client"+id+": "+textField.getText());
            }
        }

    };
}
