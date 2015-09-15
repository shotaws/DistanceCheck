import java.io.*;
import java.util.ArrayList;

//2次元座標
class XandY{
	private int name;
	private double[] node = new double[2];
	
	XandY(){
		setName(0);
		setX(0.0f);
		setY(0.0f);
	}
	
	XandY(int name, int x, int y){
		setName(name);
		setX(x);
		setY(y);
	}	
	
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public double getX() {
		return this.node[0];
	}

	public void setX(double X) {
		this.node[0] = X;
	}
	
	public double getY() {
		return this.node[1];
	}

	public void setY(double Y) {
		this.node[1] = Y;
	}

}
public class DistanceCheck {
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		double Th = 0; // 通信半径
		FileReader[] in = new FileReader[2];
		BufferedReader[] br = new BufferedReader[2];
		
		try {
			in[0] = new FileReader("DistThres.txt");
			br[0] = new BufferedReader(in[0]);
			
			String line;
			//通信半径の読み取り
			while ((line = br[0].readLine()) != null) {
				Th = Integer.parseInt(line);
			}
			
            br[0].close();
            in[0].close();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			Th = 400;
		} finally { //DistThres.txtが無くても処理を行う
			try {
				in[1] = new FileReader(args[0]); //位置座標ファイル
				br[1] = new BufferedReader(in[1]);
				FileWriter out = new FileWriter("Numbers_.txt");
				BufferedWriter bw = new BufferedWriter(out);
				
				//通信半径以内のノード番号を入れる
	            ArrayList<Integer> array = new ArrayList<Integer>();
		        XandY source = null; //ソースノード
		        XandY dist = null; //ディスティネーションノード
		        int i = 0; //ソースは1個
		        
		        String line;
		        String[] strAry;
				while ((line = br[1].readLine()) != null) {
		            System.out.println(line);
		            strAry = line.split(" ");
					if(i == 0){
						source = new XandY();
			            for(int j = 0; j < strAry.length; j++){
			            	if(j == 0)
			            		source.setName(Integer.parseInt(strAry[j]));
			            	else if(j == 2)
			            		source.setX(Double.parseDouble(strAry[j]));
			            	else if(j == 3)
			            		source.setY(Double.parseDouble(strAry[j]));
			            	
			            	//System.out.println(strAry[j]);
			            }
						i = 1;
						//ソースノードは必ず入れる
						array.add(source.getName());
						//i++;
					}
					
					else{
						dist = new XandY();
			            for(int j = 0; j < strAry.length; j++){
			            	if(j == 0)
			            		dist.setName(Integer.parseInt(strAry[j]));
			            	else if(j == 2)
			            		dist.setX(Double.parseDouble(strAry[j]));
			            	else if(j == 3)
			            		dist.setY(Double.parseDouble(strAry[j]));
			            	
			            	//System.out.println("s//"+strAry[j]);
			            }
			            
			            double d; //ソースノードとディスティネーションノードとの距離
			            d = Math.sqrt((dist.getX() - source.getX()) * (dist.getX() - source.getX()) + (dist.getY() - source.getY()) * (dist.getY() - source.getY()));
			            System.out.println("d = " + d);
			            //通信半径以内のノードを入れる
			            if(d <= Th)
			            	array.add(dist.getName());
					}
				}
				
				for(int a:array){
					System.out.println(a);
					bw.write(String.valueOf(a));
					bw.newLine();
				}
				
				bw.close();
				out.close();
	            br[1].close();
	            in[1].close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
