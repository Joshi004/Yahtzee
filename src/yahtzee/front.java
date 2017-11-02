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
GLabel score[][]=new GLabel[18][playerName.length+1];
GRect cover[][]=new GRect[18][playerName.length+1];

setup(playerName,score,cover);
/*
rollDice();
selectDice();
selectDice();
assignScore();
*/

	
}// Main ends 	

private int setup(String playerName[],GLabel score[][],GRect cover[][])
{
diceRaw[0]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/0.png");
diceRaw[1]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/1.png");
diceRaw[2]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/2.png");
diceRaw[3]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/3.png");
diceRaw[4]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/4.png");
diceRaw[5]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/5.png");
diceRaw[6]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/6.png");

double coverW=windowW/5;

double y=0,x=0;
for(int i=0;i<5;i++)		
{	
	dice[i]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/3.png");	
	dice[i].setSize(windowW/20,windowW/20);
	y=y+2*dice[0].getHeight()/1.25;
	x=dice[0].getWidth();
	add(dice[i],x,y);
	
}
x=windowW/4;
y=2*dice[0].getHeight();


// Making First Column
for(int i=0;i<18;i++)
{
	int j=0;
	
	score[i][j]=new GLabel("Large",x-30,y);
	score[i][j].setColor(Color.BLUE);
	//score[i][j].sendToFront();
	score[i][j].setFont("Serif-20");
	add(score[i][j]);
	
	
	
	cover[i][j] = new GRect(score[i][j].getWidth(),score[i][j].getHeight());
	cover[i][j].setSize(coverW,score[i][j].getHeight());
	double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2;
	//cover[i][j].sendBackward();
	add(cover[i][j],x-subs,y-score[i][j].getHeight()+score[i][j].getDescent());
	x=x+score[i][j].getWidth()+10;


y=y+score[0][0].getHeight();
x=windowW/4;
}

// making First Row

x=windowW/5+cover[0][0].getWidth();
y=2*dice[0].getHeight();

for(int j=1;j<playerName.length+1;j++)
{
int i=0;

x=cover[i][j-1].getX()+cover[i][j-1].getWidth();
double width=3*coverW/playerName.length;

score[i][j]=new GLabel("Naresh is me",x,y);
score[i][j].setColor(Color.BLUE);
score[i][j].sendToFront();
score[i][j].setFont("Serif-20");
add(score[i][j],x+width/2-score[i][j].getWidth()/2,y);


cover[i][j] = new GRect(width,score[i][j].getHeight());
double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2;
cover[i][j].sendBackward();
add(cover[i][j],x,y-score[i][j].getHeight()+score[i][j].getDescent());

}

// Adding Score Values
for(int i=1;i<18;i++)
{
for(int j=1;j<playerName.length+1;j++)
{
	x=cover[i][j-1].getX()+cover[i][j-1].getWidth(); // To start from the end of previous rect
	y=score[i][j-1].getY();
	double width=3*coverW/playerName.length;		//Window/6 is default lnegth choosen for first column and score will extend only to 3 times of it and will be decided by the number of players

	score[i][j]=new GLabel("10",x,y);
	score[i][j].setColor(Color.BLUE);
	score[i][j].sendToFront();
	score[i][j].setFont("Serif-20");
	add(score[i][j],x+width/2-score[i][j].getWidth()/2,y);

	y=cover[i][j-1].getY();
	cover[i][j] = new GRect(width,score[i][j].getHeight());
	double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2;
	cover[i][j].sendBackward();
	add(cover[i][j],x,y);
	
}
}

// Assigning names in table
for(int j=1;j<playerName.length+1;j++)
{
int i=0;
score[i][j].setLabel(playerName[j-1].toUpperCase());
score[i][j].setLocation(cover[i][j].getX()+(cover[i][j].getWidth()-score[i][j].getWidth())/2,score[i][j].getY());
}

// Assigning text to column 1 labels
score[0][0].setLabel("CATEGORIES");
score[0][0].setLocation(score[0][0].getX()-40, score[0][0].getY());
		
score[1][0].setLabel("Ones");
score[2][0].setLabel("Twos");
score[3][0].setLabel("Threes");
score[4][0].setLabel("Fours");
score[5][0].setLabel("Fives");
score[6][0].setLabel("Sixes");

score[7][0].setLabel("UPPER SCORE");
score[7][0].setLocation(score[7][0].getX()-40, score[7][0].getY());
score[8][0].setLabel("UPPER BONUS (35)");
score[8][0].setLocation(score[8][0].getX()-40, score[8][0].getY());

score[9][0].setLabel("Three Of A Kind");
score[10][0].setLabel("Four Of A Kind");
score[11][0].setLabel("Full House (25)");
score[12][0].setLabel("Small Straight (30)");
score[13][0].setLabel("Large Straight (40)");
score[14][0].setLabel("Yahtzee ! (50)");
score[15][0].setLabel("Chance");

score[16][0].setLabel("LOWER SCORE");
score[16][0].setLocation(score[16][0].getX()-40, score[16][0].getY());
score[17][0].setLabel("TOTAL SCORE");
score[17][0].setLocation(score[17][0].getX()-40, score[17][0].getY());





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
