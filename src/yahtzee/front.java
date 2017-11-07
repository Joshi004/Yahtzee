//wORK on ASSIGNsCORE

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

public void run()
{	
this.setSize(windowW,windowH);
this.setBackground(Color.green);

String playerName[]=userInfo();
 
score=new GLabel[18][playerName.length+1];
cover=new GRect[18][playerName.length+1];

setup(playerName,score,cover);

while(usedList.size()<playerName.length*13)
{
for(int player=0;player<playerName.length;player++)

{
	JOptionPane.showMessageDialog(null,playerName[player]+" : Its Your Roll");
	play(playerName,player);

}// PLayer loop	

}// Total number of turns
JOptionPane.showMessageDialog(null,"GAME OVER");
}// Main ends 	

private int setup(String playerName[],GLabel score[][],GRect cover[][])
{
	GImage heading = new GImage("file:///E:/work_space/JAVA/yahtzee/images/yahtzee.png",0,0);
	heading.scale(1.5,.7);
	//heading.setColor(Color.red);
	add(heading,this.getWidth()-heading.getWidth(),0);

	info= new GLabel("Game Information will be shown Here",0,0);
	info.setFont("Serif-35-bold");
	info.setColor(Color.red);
	//		add(info,cover[0][0].getX(),this.getHeight()-info.getDescent());     NULL POINTER EXCEPTION
	//added after addition of covers so that can be alligned with X of cover 0
	
	selectNum= new GLabel("Selection Indicator",0,this.getHeight());
	selectNum.setFont("Serif-25");
	selectNum.setColor(Color.red);
	//		add(selectNum,cover[0][0].getX(),this.getHeight()-info.getDescent());     NULL POINTER EXCEPTION
	
	
	remaining= new GLabel("Remaining Rolls",0,this.getHeight());
	remaining.setFont("Serif-25");
	remaining.setColor(Color.red);
	//		add(remaining,cover[0][0].getX(),this.getHeight()-info.getDescent());     NULL POINTER EXCEPTION
	
	
	
	
	rollButton = new GImage("file:///E:/work_space/JAVA/yahtzee/images/roll2.png");
	rollButton.setSize(2*diceDim,1.75*diceDim);
	add(rollButton,this.getWidth()/2-rollButton.getWidth()/2,0);
	
	rollButton = new GImage("file:///E:/work_space/JAVA/yahtzee/images/roll1.png");
	rollButton.setSize(2*diceDim,1.8*diceDim);
	add(rollButton,this.getWidth()/2-rollButton.getWidth()/2,0);
	rollButton.setVisible(false);
	
	
	addMouseListeners();	
for(int i=0;i<5;i++)
{
diceRaw[i][0]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/0.png");
diceRaw[i][1]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/1.png");
diceRaw[i][2]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/2.png");
diceRaw[i][3]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/3.png");
diceRaw[i][4]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/4.png");
diceRaw[i][5]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/5.png");
diceRaw[i][6]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/6.png");
}
double coverW=windowW/5;

double y=0,x=0;
for(int i=0;i<5;i++)		
{	
	dice[i]=new GImage("file:///E:/work_space/JAVA/yahtzee/images/0.png");	
	dice[i].setSize(diceDim,diceDim);
	y=y+upMargin/1.25;  // Upper Margin for dice
	x=leftMargin;           // Left Margin
	add(dice[i],x,y);	
}

x=windowW/4;
y=upMargin;


// Making First Column
for(int i=0;i<18;i++)
{
	int j=0;
	
	score[i][j]=new GLabel("Large",x-30,y); // -30 used just to increase the right indent of the text 
	score[i][j].setColor(Color.BLUE);
	score[i][j].setFont("Serif-20");
	add(score[i][j]);
	
	
	
	cover[i][j] = new GRect(score[i][j].getWidth(),score[i][j].getHeight());
	cover[i][j].setSize(coverW,score[i][j].getHeight());
	double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2; // so that the text can be center alligned in the cover
	add(cover[i][j],x-subs,y-score[i][j].getHeight()+score[i][j].getDescent());
	cover[i][j].sendBackward();
	x=x+score[i][j].getWidth()+10;


y=y+score[0][0].getHeight();
x=windowW/4;
}

// making First Row

x=windowW/5+cover[0][0].getWidth();
y=upMargin;

for(int j=1;j<playerName.length+1;j++)
{
int i=0;

x=cover[i][j-1].getX()+cover[i][j-1].getWidth();
double width=3*coverW/playerName.length;

score[i][j]=new GLabel("",x,y);
score[i][j].setColor(Color.BLUE);
score[i][j].sendToFront();
score[i][j].setFont("Serif-20");
add(score[i][j],x+width/2-score[i][j].getWidth()/2,y);


cover[i][j] = new GRect(width,score[i][j].getHeight());
double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2;
cover[i][j].sendBackward();
add(cover[i][j],x,y-score[i][j].getHeight()+score[i][j].getDescent());
cover[i][j].sendBackward();
}

// Adding Score Values
for(int i=1;i<18;i++)
{
for(int j=1;j<playerName.length+1;j++)
{
	x=cover[i][j-1].getX()+cover[i][j-1].getWidth(); // To start from the end of previous rect
	y=score[i][j-1].getY();
	double width=3*coverW/playerName.length;		//Window/6 is default lnegth choosen for first column and score will extend only to 3 times of it and will be decided by the number of players

	score[i][j]=new GLabel("00",x,y);
	score[i][j].setColor(Color.BLUE);
	score[i][j].sendToFront();
	score[i][j].setFont("Serif-20");
	add(score[i][j],x+width/2-score[i][j].getWidth()/2,y);

	y=cover[i][j-1].getY();
	cover[i][j] = new GRect(width,score[i][j].getHeight());
	double subs =(cover[i][j].getWidth()-score[i][j].getWidth())/2;
	add(cover[i][j],x,y);
	cover[i][j].sendBackward();
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
score[8][0].setLabel("UPPER BONUS	  (35)");
score[8][0].setLocation(score[8][0].getX()-40, score[8][0].getY());

score[9][0].setLabel("Three Of A Kind");
score[10][0].setLabel("Four Of A Kind");
score[11][0].setLabel("Full House	  (25)");
score[12][0].setLabel("Small Straight (30)");
score[13][0].setLabel("Large Straight (40)");
score[14][0].setLabel("Yahtzee ! 	  (50)");
score[15][0].setLabel("Chance");

score[16][0].setLabel("LOWER SCORE");
score[16][0].setLocation(score[16][0].getX()-40, score[16][0].getY());
score[17][0].setLabel("TOTAL SCORE");
score[17][0].setLocation(score[17][0].getX()-40, score[17][0].getY());

// Colouring the special rows 
for(int k=0;k<1;k++)// This loop is constructed so that aar scope ends with this loop only othewise it would exist as long as the setup function works
{
int arr[]= {0,7,8,16,17};
for(int i:arr)
{
	for(int j=0;j<playerName.length+1;j++)
{
cover[i][j].setFilled(true);
cover[i][j].setFillColor(Color.blue);
score[i][j].sendToFront();
score[i][j].setColor(Color.WHITE);
score[i][j].setFont("Serif-20-BOLD");
}}}

// Ading Info Labels
//add(info,cover[0][0].getX()+20,this.getHeight()-info.getDescent()); // 20 is margin to seperate from previous label
add(info,0,upMargin/2); // 20 is margin to seperate from previous label

add(selectNum,cover[0][0].getX(),cover[0][0].getY()-selectNum.getDescent());
add(remaining,0,this.getHeight()-selectNum.getDescent());
return 1;
}

private String[] userInfo()
{
	// Entering player Data number and names
	int playerNum=261151; // \must lie from 1-4 // given a random value which user should not eneter otherwise BAD THINGS HAPPEN
	while(true)
	{
	String msg="Please Enter number of players";
	try
	{
	playerNum=Integer.parseInt(JOptionPane.showInputDialog(null,msg));
	}
	catch(Exception e)
	{
		
	}
	if(playerNum>=2 && playerNum<5)
		break;
	else if(playerNum==261151)
	JOptionPane.showMessageDialog(null, "Please Enter a valid integer value", "Error", JOptionPane.PLAIN_MESSAGE, null);
	else if(playerNum<2)
	JOptionPane.showMessageDialog(null, "This game cant be played with less than two players", "Error", JOptionPane.PLAIN_MESSAGE, null);
	else if(playerNum>=5)
	JOptionPane.showMessageDialog(null, "This game cant be played with more than four players", "Error", JOptionPane.PLAIN_MESSAGE, null);
	
	//else if()	
	}
	String playerName[]=new String[playerNum];

	for(int i=0;i<playerNum;i++)
	{
	playerName[i]=JOptionPane.showInputDialog(null,"Enter name : "+(i+1));
	
	if(playerName[i].equalsIgnoreCase(""))
		playerName[i]="Player "+(i+1);
	
	if(playerName[i].length() > 60/playerNum)
	playerName[i]=playerName[i].substring(0,60/playerNum);
	}

return playerName;	
}// user info method ends

private void play(String playerName[],int player)
{
scoreLocked=false;
rolling=false;
int rollNum=1;
int diceValue[]=null;
	
	while(rollNum<=3 || ( !scoreLocked)  || (rollNum==4 && scoreLocked) )
	{
		
		if(rolling && rollNum<=4)
		{
		diceValue=rollnSelect(rollNum);
		if(rollNum<=3) // Because we dont want Remaing rolls to go down below 0 moreover it will make the value of rollNum 5 for which the code is not designed because there is a case for rollNum 4 in roll method where it doesnt actualy rolls but just eturns the values of existing dices 
		rollNum++;
		rolling=false;
		}

		if(moving && rollNum>1)
		moveDice();
		else if(rollNum==1)
		moving=false; // If this is not done At every players first turn if dice is selected before roll button then roll button wont work

//Code for assigning score
		if((scoreLocked) && rollNum!=1)
		{
		rollNum=assignScore(playerName,diceValue,player+1,rollNum);
		if(rollNum<=3)
			scoreLocked=false;
		
		}
			else if(scoreLocked && rollNum==1)
			{
			JOptionPane.showMessageDialog(null,"You haven't rolled yet "+playerName[player]);
			scoreLocked=false;
			}

// Code for glance
	
		if(looking && rollNum!=1)
		{
		rollNum=glance(playerName,diceValue,player+1,rollNum);
		looking=false;
		}
			else if(looking && rollNum==1)
			{
			looking=false;
			}

// Code For setting Up The Labels	
	
		if(rollNum==1)
		{
		info.setLabel("Press ROLL to roll all dices");
		selectNum.setLabel("SELECTION DISABLED");
		}
			else
			info.setLabel("Select the dices and press ROLL button");

remaining.setLabel("	Rolls Remaining : "+(4-rollNum));

}// while loop
	

}

private int[] rollnSelect(int rollNum)
{
int diceValue[]=new int[5];
ArrayList<Integer> diceNum = new ArrayList<Integer>();
diceNum=selectDice(rollNum);
diceValue=roll(diceNum,rollNum);
return diceValue;
}// RollDice function ends

private ArrayList selectDice(int roll)
{

ArrayList<Integer> diceNum = new ArrayList<Integer>();	

// For First roll all dices must move to right simultaniously and automatically
if(roll==1) 
{
	selectNum.setLabel("Selecting All");
	int dx=2;
	int dy=0;
	for(int k=0;k<5;k++)
	{
		if(dice[k].getX()<leftMargin+40) // To Make sure that at every players turn only those dice should move which are already on the left 
		{
			for(int i=0;i<40;i++)
			{
				dice[k].move(dx,dy);
				pause(5);
			}
		}
	}
}// Object inside this loop is a dice	



for(int i=0;i<5;i++)
{
if(dice[i].getX()>diceDim && !diceNum.contains(i)) // Dice is on right side and dice value or its a first roll and value is not in the list = add value
	diceNum.add(i);
if(dice[i].getX()<=diceDim && diceNum.contains(i))
	diceNum.remove(i);
}
selectNum.setLabel("No. Of Selections : "+diceNum.size());
return diceNum;
}

private int[] roll(ArrayList diceNumber,int rollNum)
{
	int diceValue[]=new int[5];
	if(rollNum<=3)  // In main roll method is working till roll num 4 but it shouldnt actualy roll for 4 rather just return the existing dice values
	{
	double x=0,y=0; // For storing positions of dice
	RandomGenerator random = RandomGenerator.getInstance();
	
	ArrayList<Integer> diceNum = new ArrayList<Integer>();
	diceNum=diceNumber;
	
	for(int rep=0;rep<100;rep++) // To Animate 
	{
		int rand,diceDelay=15;
		pause(diceDelay);
		for(int i:diceNum)		
		{	
		x=dice[i].getX();
		y=dice[i].getY();
		
		rand = random.nextInt(1,6);
		diceValue[i]=rand;
		remove(dice[i]);
		dice[i]=diceRaw[i][rand];	
		dice[i].setSize(diceDim,diceDim);
		add(dice[i],x,y);		
		}
	}
	}
	else 
	{
		for(int i=0;i<5;i++)
		{
			for(int raw=1;raw<=6;raw++)
			{
				if(dice[i].equals(diceRaw[i][raw]))
					diceValue[i]=raw;
			}
		}
	}
	
rolling=false;
return diceValue;
}

private void moveDice()
{
	if(selected.getX()>leftMargin)
	{
		dx=-2;
		dy=0;	
	}
	else
	{
		dx=2;
		dy=0;
	}
	for(int i=0;i<40;i++)
	{
		selected.move(dx,dy);
		pause(5);
	}	
moving=false;

// Code to check the number of dices that are selected
int number=0;
for(int i=0;i<5;i++)
{	
if(dice[i].getX()>diceDim )
	number++;
}
selectNum.setLabel("No. Of Selections : "+number);

}// Move Dice Method End

private int assignScore(String playerName[],int diceValue[],int cp,int roll)
{
int con=0;  // Configuration or catagory
int newRoll=roll;
ArrayList<Integer> ignore = new ArrayList<Integer>(); 
ignore.add(0);
ignore.add(7);
ignore.add(8);
ignore.add(16);
ignore.add(17);


for(con=0;con<18;con++) // Getting in this loop means any configuration se selected that is not already in the selection list
	{
		if(!ignore.contains(con) && !usedList.contains(playerName[cp-1]+con) && (selected.equals(cover[con][0]) || (selected.equals(score[con][0]))  ))
		{
		cover[con][cp].setFilled(true);
		cover[con][cp].setFillColor(Color.DARK_GRAY);
		usedList.add(playerName[cp-1]+con);
		newRoll=updateScore(con,cp,diceValue,newRoll,false);
		break;
		}	
	}// fFor loop ends	
return newRoll;
}// Assign score end

private int glance(String playerName[],int diceValue[],int cp,int roll)
{
int con=0;
int newRoll=roll;

ArrayList<Integer> ignore = new ArrayList<Integer>(); 
ignore.add(0);
ignore.add(7);
ignore.add(8);
ignore.add(16);
ignore.add(17);


for(con=0;con<18;con++)
	{
		if(!ignore.contains(con) && !usedList.contains(playerName[cp-1]+con) && (selected.equals(cover[con][0]) || (selected.equals(score[con][0]))  || (selected.equals(cover[con][cp])) || (selected.equals(score[con][cp])) ))
			{
			cover[con][cp].setFilled(true);
			cover[con][cp].setFillColor(Color.GRAY);
			cover[con][0].setFilled(true);
			cover[con][0].setFillColor(Color.yellow);
		
				if(precon-con!=0 && !usedList.contains(playerName[cp-1]+precon) && !ignore.contains(precon))
				{		
				cover[precon][cp].setFilled(false);
				cover[precon][0].setFilled(false);
				score[precon][cp].setLabel("00");	
				}	
			precon=con;			
			break;
			}	
	}// fFor loop ends	
if(con<18) // Otherwise gives array out of bound 18 exception
	if(!ignore.contains(con) && !usedList.contains(playerName[cp-1]+con) && (selected.equals(cover[con][0]) || (selected.equals(score[con][0]))  ))
		updateScore(con,cp,diceValue,newRoll,true);

return newRoll;
}// Assign score end

private int updateScore(int con,int cp,int diceValue[],int newRoll,boolean check)
{
int number[]=new int[5];
String previous="";
previous=previous+score[con][cp].getLabel();
int ones=0,twos=0,threes=0,fours=0,fives=0,sixes=0;	
	for(int i=0;i<5;i++)
		{
		if(dice[i]==diceRaw[i][1])
			{
			number[i]=1;
			ones++;
			}
		if(dice[i]==diceRaw[i][2])
			{
			number[i]=2;
			twos++;
			}
		if(dice[i]==diceRaw[i][3])
			{
			number[i]=3;
			threes++;
			}
		if(dice[i]==diceRaw[i][4])
			{
			number[i]=4;
			fours++;
			}
		if(dice[i]==diceRaw[i][5])
			{
			number[i]=5;
			fives++;
			}
		if(dice[i]==diceRaw[i][6])
			{
			number[i]=6;
			sixes++;	
			}
		}// For loop ends For all five dices
	
boolean twoKind 	= 	(ones>=2 || twos>=2 || threes>=2 || fours>=2 || fives>=2 || sixes>=2);
boolean threeKind 	=	(ones>=3 || twos>=3 || threes>=3 || fours>=3 || fives>=3 || sixes>=3);
boolean fourKind 	= 	(ones>=4 || twos>=4 || threes>=4 || fours>=4 || fives>=4 || sixes>=4);
boolean fiveKind 	= 	(ones>=5 || twos>=5 || threes>=5 || fours>=5 || fives>=5 || sixes>=45);

boolean smallStraight=false,largeStraight=false;

// To check small and large straight
int temp=0,pile=0,max=0;
int arr[]= {0,0,0,0,0,0};

	for(int k=1;k<=6;k++)
		{
		for(int i=0;i<5;i++)
			{
			if(number[i]==k)
				arr[k-1]=k;
			}
		}

	for(int i=0;i<6;i++)
		{
		if(arr[i]!=0)
			pile++;
		else
			{
			if(max<pile)
				max=pile;
			pile=0;
			}
		}

	if(max>=4)
		{
		largeStraight=true;
		smallStraight=true;
		}
	else if(max==3)
		smallStraight=true;	



	switch(con)
	{
		case 1:
				score[con][cp].setLabel(""+ones);	
		break;
		case 2:
				score[con][cp].setLabel(""+twos*2);	
		break;
		case 3:
				score[con][cp].setLabel(""+threes*3);	
		break;
		case 4:
				score[con][cp].setLabel(""+fours*4);	
		break;
		case 5:
				score[con][cp].setLabel(""+fives*5);	
		break;
		case 6:
				score[con][cp].setLabel(""+sixes*6);	
		break;
		case 9:
				if(threeKind)
					{
					int sum=0;
					for(int i=0;i<5;i++)
						{
						sum+=number[i];
						}
					score[con][cp].setLabel(""+sum);
					}
		break;
		case 10:
			if(fourKind)
				{
				int sum=0;
				for(int i=0;i<5;i++)
					{
					sum+=number[i];
					}
				score[con][cp].setLabel(""+sum);
				}
		break;
		case 11:
			if(threeKind && twoKind)
				score[con][cp].setLabel(""+25);
		break;
		case 12:
			if((smallStraight || largeStraight))
				score[con][cp].setLabel(""+30);
		break;
		case 13:
			if(largeStraight)
				score[con][cp].setLabel(""+40);
		break;
		case 14:
			if(fiveKind)
			score[con][cp].setLabel(""+50);
		break;
		
		case 15:
				int sum=0;
				for(int i=0;i<5;i++)
					sum+=number[i];
				score[con][cp].setLabel(""+sum);
		break;
	}	

	if(!check)
	{
		int ls=Integer.parseInt(score[7][cp].getLabel());
		int us=Integer.parseInt(score[16][cp].getLabel());
		int bonus=Integer.parseInt(score[8][cp].getLabel());
		int total=Integer.parseInt(score[17][cp].getLabel());
		
				if(con<7 && con>0)
			{
			int x=Integer.parseInt(score[7][cp].getLabel());
			int y=Integer.parseInt(score[con][cp].getLabel());
			us=x+y;
			score[7][cp].setLabel(""+us);
			}
		if(con>6 && con<16)
			{
			int x=Integer.parseInt(score[16][cp].getLabel());
			int y=Integer.parseInt(score[con][cp].getLabel());
			ls=x+y;	
			score[16][cp].setLabel(""+ls);
			}
		if(ls>62)
			bonus=35;
		score[8][cp].setLabel(""+bonus);
		
		total=(Integer.parseInt(score[7][cp].getLabel()))+(Integer.parseInt(score[16][cp].getLabel()))+bonus;
		score[17][cp].setLabel(""+total);
		
		newRoll=5; // It means that the Score is Updated and now no more rolls are required so change it to 4
	}
	return newRoll;
}

public void mousePressed(MouseEvent event)
{
GObject obj=null;
if(getElementAt(event.getX(),event.getY())!=null && !moving)
	{
	obj=getElementAt(event.getX(),event.getY());
	int x=event.getX();
	int y= event.getY();
	if((obj.equals(dice[0])) || (obj.equals(dice[1])) || (obj.equals(dice[2])) || (obj.equals(dice[3])) || (obj.equals(dice[4])) )
		{
		selected=obj;
		moving=true;
		}// Object inside this loop is a dice

	else if(obj.equals(rollButton))
		{
		rolling=true;
		}
	else if( x>=cover[0][0].getX()  &&  x<=cover[0][0].getX()+cover[0][0].getWidth()  &&  y>=cover[0][0].getY()  &&  y<=cover[17][0].getY()+cover[17][0].getWidth())
		{
		selected=obj;
		scoreLocked=true;
		}
	}
}

public void mouseMoved(MouseEvent event)
{
	GObject obj=null;
	int x=event.getX();
	int y= event.getY();
	if(getElementAt(event.getX(),event.getY())!=null && !rolling &&  !moving)
		{
		obj = getElementAt(event.getX(),event.getY());
	
		if(obj==rollButton)
			rollButton.setVisible(true);
	
	
		else if( x>=cover[0][0].getX()  &&  x<=cover[0][0].getX()+cover[0][0].getWidth()  &&  y>=cover[0][0].getY()  &&  y<=cover[17][0].getY()+cover[17][0].getWidth())
			{
			rollButton.setVisible(false);
			selected=obj;
			looking=true;
			}
		else
			rollButton.setVisible(false);
		}


	else
		rollButton.setVisible(false); // So that as soon as mouse shows null also button one is restored
}


// Class variables fOr the game 

GLabel info,selectNum,remaining,score[][];
GImage dice[]=new GImage[5],rollButton,diceRaw[][]=new GImage[5][7];
GObject selected=null;
GRect cover[][];

ArrayList<String> usedList = new ArrayList<String>(); 
int windowH=640,windowW=1340,dx=0,dy=0,precon;
double diceDim=windowW/20;	
double leftMargin=diceDim,upMargin=2*diceDim;  // Howevr upMargin for dice is 2*diceDim/1.25

boolean rolling=false,moving=false,scoreLocked=false,looking; 
//Indicate that different buttons are click like move dice or locking any score or roll dice so that corosponding functions can run in main 


}//class yethzee front ends

