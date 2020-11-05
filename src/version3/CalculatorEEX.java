package version3;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorEEX extends JFrame {
	private static final long serialVersionUID = 1L;

	Font type=new Font("黑体",30,30);
    Font type2=new Font("黑体",20,20);
    JTextField sub = new JTextField(null);
    JTextField obj = new JTextField(null);
    JTextField op = new JTextField(null);
    JTextField M = new JTextField(null);
    JButton tran=new JButton();
    Container c=getContentPane();
    JButton key[]=new JButton[25];
    StringBuilder eflag=new StringBuilder("");
    StringBuilder tflag=new StringBuilder("");
    StringBuilder mflag=new StringBuilder("0");
    StringBuilder memory = new StringBuilder("0");
    String[] Keys= {"M+","M-","MR","MC","C","7","8","9","/","CE","4","5","6","×","DEL","1","2","3","-","%","0",".","00","+","="};
    MusicStuff musicObject = new MusicStuff();
	
	public CalculatorEEX() {
        super("简易计算器");
        
		JButton timebutton=new JButton();
        timebutton.setBackground(Color.WHITE);
        timebutton.setFocusable(false);
        timebutton.setFont(type2);
        timebutton.setText("T");
        timebutton.setHorizontalAlignment(JButton.CENTER);
        timebutton.setPreferredSize(new Dimension(50,50));
        c.add(timebutton);
        
        JLabel time = new JLabel();
		time.setForeground(Color.BLUE);
		time.setPreferredSize(new Dimension(340,70));
		time.setFont(type);
		time.setHorizontalAlignment(JLabel.CENTER);
		c.add(time);
		this.setTimer(time);
		
        JButton musicbutton=new JButton();
        musicbutton.setBackground(Color.WHITE);
        musicbutton.setFocusable(false);
        musicbutton.setFont(type2);
        musicbutton.setText("M");
        musicbutton.setHorizontalAlignment(JButton.CENTER);
        musicbutton.setPreferredSize(new Dimension(50,50));
		c.add(musicbutton); 
        
        tran.setBackground(Color.WHITE);
        tran.setFocusable(false);
        tran.setFont(type2);
        tran.setText("G");
        tran.setHorizontalAlignment(JButton.CENTER);
        sub.setFont(type);
        obj.setFont(type);
        op.setFont(type);
        M.setFont(type2);
        sub.setHorizontalAlignment(JTextField.RIGHT);
        obj.setHorizontalAlignment(JTextField.RIGHT);
        op.setHorizontalAlignment(JTextField.RIGHT);
        M.setHorizontalAlignment(JTextField.CENTER);
        c.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        c.add(tran);
        c.add(sub);
        c.add(M);
        c.add(obj);
        c.add(op);
        M.setPreferredSize(new Dimension(50,70));
        sub.setPreferredSize(new Dimension(340,70));
        tran.setPreferredSize(new Dimension(50,50));
        obj.setPreferredSize(new Dimension(390,70));
        op.setPreferredSize(new Dimension(55,70));
        sub.setEditable(false);
        obj.setEditable(false);
        op.setEditable(false);
        M.setEditable(false);
        
        timebutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (tflag.toString().equals("")) {
        			tflag.append("T");
        		}
        		else  {
        			tflag.delete(0, tflag.length());
        		}
        	}
        });
        
        musicbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (mflag.toString().equals("5")) {
        			mflag.delete(0, mflag.length());
        			mflag.append("0");
        			musicObject.playMusic(".//src//version3//music//audio5.wav",0);
        		}
        		else  {
        			int musicnum=Integer.parseInt(mflag.toString());
        			String oldpath = ".//src//version3//music//audio"+musicnum+".wav";
        			musicnum++;
        			mflag.delete(0, mflag.length());
        			mflag.append(musicnum);
        			String filepath = ".//src//version3//music//audio"+musicnum+".wav";
        			musicObject.playMusic(oldpath,0);
        			musicObject.playMusic(filepath,musicnum);
        			sub.setText("正在播放:音乐"+musicnum);
        		}
        	}
        });
        
        
        tran.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (tran.getText().equals("G")) {
        			tran.setText("S");
        			
        		}
        		else if (tran.getText().equals("S")) {
        			tran.setText("G");
        		}
        	}
        });
       
        for (int i=0;i<25;i++) {
            key[i] = new JButton();
            key[i].setBackground(Color.WHITE);
            key[i].setFocusable(false);
            key[i].setPreferredSize(new Dimension(86,86));
            key[i].setFont(type);
            c.add(key[i]);
            key[i].setText(Keys[i]);
        }
        key[4].setBackground(Color.ORANGE);
        this.setResizable(false);
        
        JButton num[]={key[15],key[16],key[17],key[10],key[11],key[12],key[5],key[6],key[7]};
        for (int i=0;i<num.length;i++) {
            num[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
        			if (sub.getText().contains("正在播放:音乐")) {
        				sub.setText("");
        			}
                    JButton action_Button=(JButton) e.getSource();
                    String ef=eflag.toString();
                    if (ef.equals("F")) {
                    	sub.setText(null);
                    	obj.setText(null);
                    	op.setText(null);
                    	eflag.delete(0, 1);
                    	obj.setText(obj.getText() + action_Button.getText());
                    }
                    else {
                    	if (obj.getText().equals("0")) {
                    		obj.setText(action_Button.getText());
                    	}
                    	else {
                    		obj.setText(obj.getText() + action_Button.getText());
                    	}
                    }
                }
            });
        }
        key[20].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		JButton action_Button=(JButton) e.getSource();
        		String temp=obj.getText();
        		if (temp.equals("")) {
        			obj.setText("0");
        		}
        		else if (temp.equals("0")) {
        			obj.setText("0");
        		}
        		else {
        			obj.setText(temp+action_Button.getText());
        		}
        	}
        });
        key[22].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		JButton action_Button=(JButton) e.getSource();
        		String temp=obj.getText();
        		if (temp.equals("")) {
        			obj.setText("0");
        		}
        		else if (temp.equals("0")) {
        			obj.setText(temp);
        		}
        		else {
        			obj.setText(temp+action_Button.getText());
        		}
        	}
        });
        
        key[21].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		JButton action_Button = (JButton) e.getSource();
        		String temp=obj.getText();
        		if (temp.contains(".")) {
        			obj.setText(temp);
        		}
        		else if (temp.equals("")) {
        			obj.setText("0.");
        		}
        		else {
        			obj.setText(temp+action_Button.getText());
        		}
        	}
        });
        
        for (int j=8;j<24;j=j+5) {
            key[j].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
        			if (sub.getText().contains("正在播放:音乐")) {
        				sub.setText("");
        			}
                	String temp1=obj.getText();
                    String temp2=sub.getText();
                	eflag.delete(0, 1);
                	String error="^[0-9]+\\.$";
                	Pattern p=Pattern.compile(error);
        			Matcher m=p.matcher(obj.getText());
        			if (m.find()) {
        				sub.setText(null);
                    	obj.setText("ERROR");
                    	op.setText(null);
                    	for (int i=0;i<25;i++) {
                        	key[i].setEnabled(false);
                        }
                        key[4].setEnabled(true);
        			}
        			else if (tran.getText().equals("G")) {
                        if (temp1.isEmpty() && temp2.isEmpty()) {
                        	sub.setText("0");
                        	op.setText(((JButton) e.getSource()).getText());
                        	obj.setText(null);
                        }
                        else if (!temp1.isEmpty() && temp2.isEmpty()){
                        	sub.setText(obj.getText());
                        	op.setText(((JButton) e.getSource()).getText());
                        	obj.setText(null);
                        }
                        else if (temp1.isEmpty() && !temp2.isEmpty()) {
                        	op.setText(((JButton) e.getSource()).getText());
                        }
                        else {
                        	String temp3=op.getText();
                            if (!temp1.isEmpty() && !temp2.isEmpty() && !temp3.isEmpty()) {
                            	try {
                                    String result=ComputeEX.result(sub.getText(),obj.getText(),op.getText());
                                    obj.setText(null);
                                    op.setText(((JButton) e.getSource()).getText());
                                    sub.setText(result);
                                } catch(ArithmeticException ex) {
                                    sub.setText(null);
                                    obj.setText("ERROR");
                                    op.setText(null);
                                    for (int i=0;i<25;i++) {
                                    	key[i].setEnabled(false);
                                    }
                                    key[4].setEnabled(true);
                                }catch(RuntimeException r) {
                                	sub.setText(null);
                                    obj.setText("OverFlow");
                                    op.setText(null);
                                    for (int i=0;i<25;i++) {
                                    	key[i].setEnabled(false);
                                    }
                                    key[4].setEnabled(true);
                                }
                            }
                        }
                    }
                	else {
                		if (sub.getText().equals("") && obj.getText().equals("")) {
                			sub.setText("0");
                			op.setText(((JButton) e.getSource()).getText());
                		}
                		else if (!sub.getText().equals("") && obj.getText().equals("")) {
                			op.setText(((JButton) e.getSource()).getText());
                		}
                		else if (!sub.getText().equals("") && !obj.getText().equals("")) {
                			String tempsub=sub.getText();
                			sub.setText(tempsub+op.getText()+obj.getText());
                			op.setText(((JButton) e.getSource()).getText());
                			obj.setText(null);
                		}
                		else if (sub.getText().equals("") && !obj.getText().equals("")) {
                			sub.setText(obj.getText());
                			obj.setText(null);
                			op.setText(((JButton) e.getSource()).getText());
                		}
                	}
                }
            });
        }
                                                                                                                                                             
        key[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sub.setText(null);
                obj.setText(null);
                op.setText(null);
                for (int i=0;i<25;i++) {
                	key[i].setEnabled(true);
                }
            }
        }
        );

        key[9].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
                obj.setText(null);
            }
        }
        );
        
        key[14].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
                String origin=obj.getText();
                if (origin.length()==0) {
                	obj.setText(origin);
                }
                else {
                	String now=origin.substring(0, origin.length()-1);
                	obj.setText(now);
                }
            }
        }
        );
        
        key[19].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
            	String temp1=obj.getText();
            	if (!temp1.isEmpty()) {
            		String result=ComputeEX.result(temp1, "100", "/");
            		obj.setText(result);
            	}
            	else {
            		sub.setText(null);
                    obj.setText("ERROR");
                    op.setText(null);
                    for (int i=0;i<25;i++) {
                    	key[i].setEnabled(false);
                    }
                    key[4].setEnabled(true);
            	}
            }
        }
        );
        
        key[24].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
            	String temp1=obj.getText();
                String temp2=sub.getText();
                String temp3=op.getText();
                String error="^[0-9]+\\.$";
            	Pattern p=Pattern.compile(error);
    			Matcher m=p.matcher(obj.getText());
    			if (m.find()) {
    				sub.setText(null);
                	obj.setText("ERROR");
                	op.setText(null);
                	for (int i=0;i<25;i++) {
                    	key[i].setEnabled(false);
                    }
                    key[4].setEnabled(true);
    			}
    			else if (tran.getText().equals("G")) {
                    if (!temp1.isEmpty() && !temp2.isEmpty() && !temp3.isEmpty()) {
                    	try {
                            String result=ComputeEX.result(sub.getText(),obj.getText(),op.getText());
                            sub.setText(null);
                            obj.setText(result);
                            op.setText(null);
                            eflag.append("F");
                        } catch(ArithmeticException ex) {
                            sub.setText(null);
                            obj.setText("ERROR");
                            op.setText(null);
                            for (int i=0;i<25;i++) {
                            	key[i].setEnabled(false);
                            }
                            key[4].setEnabled(true);
                        }catch(RuntimeException r) {
                        	sub.setText(null);
                            obj.setText("OverFlow");
                            op.setText(null);
                            for (int i=0;i<25;i++) {
                            	key[i].setEnabled(false);
                            }
                            key[4].setEnabled(true);
                        }
                    }
                }
                else {
                	if (!temp1.isEmpty() && !temp2.isEmpty() && !temp3.isEmpty()) {
                    	try {
                    		sub.setText(temp2+temp3+temp1);
                            String result=ExecuteEX.result(sub.getText());
                            double res=Double.parseDouble(result);
                            if (Math.abs(res)>1e10) {
                            	sub.setText(null);
                                obj.setText("OverFlow");
                                op.setText(null);
                                for (int i=0;i<25;i++) {
                                	key[i].setEnabled(false);
                                }
                                key[4].setEnabled(true);
                            }
                            else {
                            	if (Math.abs(res)<1e-10) {
                            		result="0";
                            	}
                                sub.setText(null);
                                obj.setText(result);
                                op.setText(null);
                                eflag.append("F");
                            }
                        } catch(ArithmeticException ex) {
                            sub.setText(null);
                            obj.setText("ERROR");
                            op.setText(null);
                            for (int i=0;i<25;i++) {
                            	key[i].setEnabled(false);
                            }
                            key[4].setEnabled(true);
                        }catch(RuntimeException r) {
                        	sub.setText(null);
                            obj.setText("OverFlow");
                            op.setText(null);
                            for (int i=0;i<25;i++) {
                            	key[i].setEnabled(false);
                            }
                            key[4].setEnabled(true);
                        }
                    }
                }
            }
        });
        
        key[2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		String tempmemory=memory.toString();
        		obj.setText(tempmemory);
        	}
        });
        
        key[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		if (obj.getText().equals("")) {	}
        		else {
        			String error="^[0-9]+\\.$";
                	Pattern p=Pattern.compile(error);
        			Matcher m=p.matcher(obj.getText());
        			if (!m.find()) {
        				String res=ComputeEX.result(memory.toString(), obj.getText(), "+");
        				memory.delete(0, memory.length());
        				memory.append(res);
        				double dm=Double.parseDouble(res);
        				if (dm>0) {
        					M.setText("M+");
        				}
        				else if (dm<0) {
        					M.setText("M-");
        				}
        				else {
        					M.setText("");
        				}
        			}
        		}
        	}
        });
        
        key[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		if (obj.getText().equals("")) {	}
        		else {
        			String error="^[0-9]+\\.$";
                	Pattern p=Pattern.compile(error);
        			Matcher m=p.matcher(obj.getText());
        			if (!m.find()) {
        				String res=ComputeEX.result(memory.toString(), obj.getText(), "-");
        				memory.delete(0, memory.length());
        				memory.append(res);
        				double dm=Double.parseDouble(res);
        				if (dm>0) {
        					M.setText("M+");
        				}
        				else if (dm<0) {
        					M.setText("M-");
        				}
        				else {
        					M.setText("");
        				}
        			}
        		}
        	}
        });
        
        key[3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			if (sub.getText().contains("正在播放:音乐")) {
    				sub.setText("");
    			}
        		memory.delete(0, memory.length());
        		memory.append("0");
        		M.setText("");
        	}
        });
    } 
	
	private void setTimer(JLabel time) {
		final JLabel varTime = time;
		Timer timeAction = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (tflag.toString().equals("T")) {
					varTime.setText(df.format(new Date(timemillis)));
        		}
        		else  {
        			varTime.setText("");
        		}
			}
		});
		timeAction.start();
	}
	
    public static void main(String args[]) {
    	CalculatorEEX app = new CalculatorEEX();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(480, 730);
        app.setLocation(380, 50);
        app.setVisible(true);
    }
}
