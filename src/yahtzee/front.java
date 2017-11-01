package yahtzee;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import acm.graphics.*;
import acm.gui.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class front extends GraphicsProgram
{
	
GImage dice[]=new GImage[5];
GImage diceRaw[]=new GImage[7];

int windowH=640,windowW=1340;
	
public void run()
{
Scanner input=new Scanner(System.in);

	
this.setSize(windowW,windowH);
this.setBackground(Color.green);

String playerName[]=userInfo();
int diceNum[] = new int[5]; 
GLabel score[][]=new GLabel[17][playerName.length+1];


setup(playerName,score);
/*
rollDice();
selectDice();
selectDice();
assignScore();
*/

	
}// Main ends 	

private int setup(String playerName[],GLabel score[][])
{
diceRaw[0]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/0.png");
diceRaw[1]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/1.png");
diceRaw[2]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/2.png");
diceRaw[3]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/3.png");
diceRaw[4]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/4.png");
diceRaw[5]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/5.png");
diceRaw[6]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/6.png");
double y=0,x=0;
for(int i=0;i<5;i++)		
{	
	dice[i]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/0.png");	
	dice[i].setSize(windowW/20,windowW/20);
	y=y+2*dice[0].getHeight()/1.5;
	x=dice[0].getWidth();
	add(dice[i],x,y);
}
x=windowW/3;
y=2*dice[0].getHeight();

for(int i=0;i<17;i++)
{
for(int j=0;j<playerName.length+1;j++)
{
	
	
	score[i][j]=new GLabel("hello gamer bhai",x,y);
	score[i][j].setColor(Color.BLUE);
	score[i][j].sendToFront();
	score[i][j].setFont("Serif-20");
	add(score[i][j]);
	
	
	GRect cover = new GRect(x,y-score[i][j].getHeight(),score[i][j].getWidth(),score[i][j].getHeight());
	cover.sendBackward();
	add(cover);
	x=x+score[i][j].getWidth()+10;
}
y=y+score[0][0].getHeight();
x=windowW/3;
}

return 1;
}

private String[] userInfo()
{
	// Entering player Data number and names
	int playerNum; // \must lie from 1-4
	while(true)
	{
	String msg="Please Enter number of players";
	playerNum=Integer.parseInt(JOptionPane.showInputDialog(null,msg));
	if(playerNum>0 && playerNum<5)
		break;
	JOptionPane.showMessageDialog(null, "Try again with valid values between 1 to 4", "Error", JOptionPane.PLAIN_MESSAGE, null);
	}
	String playerName[]=new String[playerNum];

	for(int i=0;i<playerNum;i++)
	{
	playerName[i]=JOptionPane.showInputDialog(null,"Enter name : "+(i+1));
	}

return playerName;	
}// user info method ends

}//class yethzee front ends


/*
Five dice & 1-4 players

configuration catagories 13 
chance			-	sum of all values
ones - six		-	sum of all corospondings 
three of a kind-	sum of all
four of a kind -	sum of all
full house 	-	25
small straight -	30
large straight	-	40
yahtzee 		-	50
 
if a player’s score for the upper section ends up totaling 63 or more, 
that player is awarded a 35-point bonus on the next line. 
The scores in the lower section of the scorecard are also added together to generate the entry labeled Lower Score. 
The total score for each player is then computed by adding together the upper score, 
the bonus (if any), and the lower score.
*/
