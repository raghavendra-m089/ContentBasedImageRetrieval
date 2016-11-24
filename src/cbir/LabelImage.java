package cbir;





import java.util.Stack;

public class LabelImage {
int [][] label ;
int [][]label1;
public int lab;
Stack stack ;
/** Creates a new instance of LabelImage */
public LabelImage() {

}
public int[][] labelImage(int[][] img2) {

int nrow = img2.length+2 ;
int ncol = img2[0].length+2 ;
 lab = 1 ;
int [] pos;
  int s;
int img[][];
//System.out.println(nrow+"dd"+ncol);
stack = new Stack() ;
label = new int[nrow][ncol] ;
img=new int[nrow][ncol];
label1=new int[nrow-2][ncol-2];
for ( int i = 1; i < nrow -1; i++)
    for (int  j = 1; j < ncol -1; j++)
      img[i][j] = img2[i - 1][j - 1];


for (int c = 1; c < ncol-1; c++)
for (int r = 1; r < nrow-1; r++){
if (img[r][c] == 0) continue ;
if (label[r][c] > 0) continue ;
/* encountered unlabeled foreground pixel at position r, c */
/* push the position on the stack and assign label */
stack.push(new int [] {r, c}) ;
label[r][c] = lab ;
  //Object jk;
/* start the float fill */
while ( !stack.isEmpty()) {
pos= (int [])stack.pop() ;
  //System.out.print(s);
int i = pos[0]; int j = pos[1];
if (img[i-1][j-1] == 1 && label[i-1][j-1] == 0) {
stack.push( new int[] {i-1,j-1} );
label[i-1][j-1] = lab ;
}
if (img[i-1][j] == 1 && label[i-1][j] == 0) {
stack.push( new int[] {i-1,j} );
label[i-1][j] = lab ;
}
if (img[i-1][j+1] == 1 && label[i-1][j+1] == 0) {
stack.push( new int[] {i-1,j+1} );
label[i-1][j+1] = lab ;
}
if (img[i][j-1] == 1 && label[i][j-1] == 0) {
stack.push( new int[] {i,j-1} );
label[i][j-1] = lab ;
}
if (img[i][j+1] == 1 && label[i][j+1] == 0) {
//Chapter Title 9
stack.push( new int[] {i,j+1} );
label[i][j+1] = lab ;
}
if (img[i+1][j-1] == 1 && label[i+1][j-1] == 0) {
stack.push( new int[] {i+1,j-1} );
label[i+1][j-1] = lab ;
}
if (img[i+1][j] == 1 && label[i+1][j] == 0) {
stack.push( new int[] {i+1,j} );
label[i+1][j] = lab ;
}
if (img[i+1][j+1] == 1 && label[i+1][j+1] == 0) {
stack.push( new int[] {i+1,j+1} );
label[i+1][j+1] = lab ;
}
} /* end while */
lab++ ;
}

  for(int i=0;i< nrow-2;i++)
    for(int j=0;j< ncol-2;j++)
            label1[i][j]=label[i+1][j+1];
return label1 ;

}
}
