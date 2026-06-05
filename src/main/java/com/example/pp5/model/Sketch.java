package com.example.pp5.model;

//とりあえず全部入れとこ、あとで直すわ
import java.util.*;

import org.eclipse.collections.impl.list.mutable.FastList;
import processing.core.*;
import wblut.math.*;
import wblut.processing.*;
import wblut.core.*;
import wblut.hemesh.*;
import wblut.geom.*;
import toxi.geom.*;

//extends PAppletは無しね
public class Sketch {
    //PAppletのインスタンスを経由しないとほとんどのメソッドが使えないんぞ
    //RestControllerでクラス型を返すとここのフィールド変数が自動でjsonになるんだって
    //のでjsに送りたくないデータがここに入ってるの困るので
    //毎回PAppletをdrawで作るかここに置くか、データ返す用のクラスは別に作った方が良さそう
    public PApplet p;

    public Sketch(){
        //ウィンドウのrun()が開始される場所確認したいわね
        //PAppletのコンストラクタ真っ白だわ、何もしてない、安全
        //PAppletの4680行からmain(){runSketch(){ウィンドウ作る}}だった
        //エントリーのmain()実行回避すればいける
        p = new PApplet();
    }

    //ループしないからsetupいらないよね〜
    //public void setup(){}

    public void draw(){
        //PApplet p = new PApplet();
    }

    public float random(){
        float r = p.random(100);
        //System.out.println(r);
        return r;
    }

    public float[][][][] voronoiBox(){
        //hemesh
        //example/hemesh/create/Ref_HEMC_VoronoiBox
        //のサンプルコードを使わせてもらいました
        WB_Point[] points;
        int numpoints;
        WB_AABB container;
        HE_MeshCollection cells;
        HE_Mesh mesh;
        WB_Render render;

        container=new WB_AABB(-300, -300, -300, 300, 300, 300);
        mesh=new HE_Mesh(new HEC_Box().setFromAABB(container));
        //numpoints=1000;
        numpoints=(int)p.random(300,800);
        points=new WB_Point[numpoints];
        WB_RandomPoint rp=new WB_RandomOnSphere();
        //セルの中心座標
        for (int i=0; i<numpoints; i++) {
            //points[i]=rp.nextPoint().mulSelf(p.random(299.9F, 300));
            points[i]=rp.nextPoint().mulSelf(p.random(10, 300));
        }

        HEMC_VoronoiBox multiCreator=new HEMC_VoronoiBox();
        multiCreator.setPoints(points);
        multiCreator.setContainer(container);
        multiCreator.setOffset(new Gradient());
        cells=multiCreator.create();

        //これはスライスでぶった斬るモデファイあ
  /*
  HE_MeshIterator mItr=cells.mItr();
   HE_Mesh m;
   while(mItr.hasNext()){
   m=mItr.next();
   m.modify(new HEM_Slice().setPlane(50,50,50,-1,-1,-1));
   m.modify(new HEM_Slice().setPlane(-50,-50,-50,1,1,1));
   }
   float d=4.0/sqrt(3.0);
   mesh.modify(new HEM_Slice().setPlane(50+d,50+d,50+d,-1,-1,-1));
   mesh.modify(new HEM_Slice().setPlane(-50-d,-50-d,-50-d,1,1,1));
   */
        //描写
        //render=new WB_Render(this);
        //drawEdges(){render.drawEdges(cells);}で辺を描いてる
        // drawFaces(){render.drawFaces(cells);}で面を描いてる
        //meshはバウンディング領域を描写してるからボロノイ本体はcellsだけ


        List<HE_Mesh> meshes = cells.toList();
        float[][][][] coords = new float[meshes.size()][][][];
        for(int i=0; i<meshes.size();i++){//HE_Mesh m : meshes
            List<HE_Face> faces = meshes.get(i).getFaces();
            //HE_Face[] faces = m.getFacesAsArray();
            //System.out.println(faces.size());//5-20
            coords[i] = new float[faces.size()][][];
            for(int j=0; j<faces.size();j++){//HE_Face f : faces
                List<HE_Vertex> vertices = faces.get(j).getFaceVertices();
                //System.out.println(vertices.size());//3-8
                coords[i][j] = new float[vertices.size()][];
                for(int k=0; k<vertices.size();k++){//HE_Vertex v : vertices
                    float x = vertices.get(k).getPosition().xf();//v.xf();でもおんなじことしてる
                    float y = vertices.get(k).getPosition().yf();
                    float z = vertices.get(k).getPosition().zf();
                    //double x = v.getPosition().xd();//v.xd();
                    //double y = v.getPosition().yd();
                    //double z = v.getPosition().zd();
                    coords[i][j][k] = new float[3];
                    coords[i][j][k][0] = x;
                    coords[i][j][k][1] = y;
                    coords[i][j][k][2] = z;
                }
            }
        }
        //System.out.println(Arrays.deepToString(coords));

        return coords;
    }

    //内部クラスにできるかなっていう,できたね
    //セルの隙間に間隔作る
    class Gradient implements WB_ScalarParameter {
        public double evaluate(double... x) {
            return  p.map((float)x[1], 200, -250, p.random(5,20), p.random(15,35));
        }
    }
}


