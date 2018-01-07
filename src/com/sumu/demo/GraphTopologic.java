package com.sumu.demo;

import java.util.Stack;

import com.sumu.demo.GraphTopologic.VertexNode;

/**
 * 拓扑排序
 * @author YaoMingliang
 * @date  2018年1月7日  上午11:32:35
 */
public class GraphTopologic {
	//边表顶点
	class EdgeNode{
		private int adjVert;//下标号
		private EdgeNode next;
		
		public EdgeNode(int adjVert) {
			// TODO Auto-generated constructor stub
			this.adjVert=adjVert;
		}
		public int getAdjVert() {
			return adjVert;
		}
		public void setAdjVert(int adjVert) {
			this.adjVert = adjVert;
		}
		public EdgeNode getNext() {
			return next;
		}
		public void setNext(EdgeNode next) {
			this.next = next;
		}
	}
	//邻接顶点
	class VertexNode{
		private int in;//入度总数
		private String data;
		private EdgeNode firstNode;
		
		
		public VertexNode(int in, String data) {
			super();
			this.in = in;
			this.data = data;
		}
		public int getIn() {
			return in;
		}
		public void setIn(int in) {
			this.in = in;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public EdgeNode getFirstNode() {
			return firstNode;
		}
		public void setFirstNode(EdgeNode firstNode) {
			this.firstNode = firstNode;
		}
	}
	private int numVertexs;
	private VertexNode[] adjList;//邻接顶点的一对数组
	public GraphTopologic(int numVertexs) {
		// TODO Auto-generated constructor stub
		this.numVertexs=numVertexs;
		adjList=new VertexNode[numVertexs];
	}
	private void createGraph() {
		VertexNode node0=new  VertexNode(0, "V0");
		VertexNode node1=new  VertexNode(0, "V1");
		VertexNode node2=new  VertexNode(2, "V2");
		VertexNode node3=new  VertexNode(0, "V3");
		VertexNode node4=new  VertexNode(2, "V4");
		VertexNode node5=new  VertexNode(3, "V5");
		VertexNode node6=new  VertexNode(1, "V6");
		VertexNode node7=new  VertexNode(2, "V7");
		VertexNode node8=new  VertexNode(2, "V8");
		VertexNode node9=new  VertexNode(2, "V9");
		VertexNode node10=new  VertexNode(1, "V10");
		VertexNode node11=new  VertexNode(2, "V11");
		VertexNode node12=new  VertexNode(1, "V12");
		VertexNode node13=new  VertexNode(2, "V13");
		
		adjList[0]=node0;
		adjList[1]=node1;
		adjList[2]=node2;
		adjList[3]=node3;
		adjList[4]=node4;
		adjList[5]=node5;
		adjList[6]=node6;
		adjList[7]=node7;
		adjList[8]=node8;
		adjList[9]=node9;
		adjList[10]=node10;
		adjList[11]=node11;
		adjList[12]=node12;
		adjList[13]=node13;
		
		node0.firstNode=new EdgeNode(11);
		node0.firstNode.next=new EdgeNode(5);
		node0.firstNode.next.next=new EdgeNode(4);
		
		node1.firstNode=new EdgeNode(8);
		node1.firstNode.next=new EdgeNode(4);
		node1.firstNode.next.next=new EdgeNode(2);
		
		node2.firstNode=new EdgeNode(9);
		node2.firstNode.next=new EdgeNode(6);
		node2.firstNode.next.next=new EdgeNode(5);
		
		node3.firstNode=new EdgeNode(13);
		node3.firstNode.next=new EdgeNode(2);
		
		node4.firstNode=new EdgeNode(7);
		
		node5.firstNode=new EdgeNode(12);
		node5.firstNode.next=new EdgeNode(8);
		
		node6.firstNode=new EdgeNode(5);
		
		node8.firstNode=new EdgeNode(7);
		
		node9.firstNode=new EdgeNode(11);
		node9.firstNode.next=new EdgeNode(10);
		
		node10.firstNode=new EdgeNode(13);

		node12.firstNode=new EdgeNode(9);
	}
	
	/**
	 * 1、首先找到入度为0的顶点，将其压入栈中（栈存放所有入度为零，可以执行的顶点）
	 * 2、然后从栈中取出一个可执行顶点，遍历查看该顶点的所有出度顶点(先将所有的出度顶点入度数减1再判断是否为0)，
	 * 如果出度顶点的入度数为零，则说明该出度顶点的入度顶点都已执行完，说明该顶点可以执行，就将其压入栈中，直到栈空为止
	 * 
	 * 核心思想：所有顶点执行都有一定的先后顺序，所以在每个顶点执行之前，需保证其入度顶点已经执行完。
	 * @throws Exception
	 */
	private void topologicalSort() throws Exception {
		int count = 0;
		Stack<Integer> vertexNodes=new Stack<>();
		for (int i = 0; i < numVertexs; i++) {
			if (adjList[i].in==0) {
				//寻找入度为0的，将其压入栈
				vertexNodes.push(i);
			}
		}
		
		while (!vertexNodes.isEmpty()) {
			int pop = vertexNodes.pop();
			System.out.println("顶点:"+adjList[pop].data);
			count++;
			for (EdgeNode edgeNode=adjList[pop].firstNode; edgeNode!=null; edgeNode=edgeNode.next) {
				int adjVert = edgeNode.adjVert;
				adjList[adjVert].in--;
				if (adjList[adjVert].in==0) {
					vertexNodes.push(adjVert);//入度为0,入栈
				}
			}
		}
		if (count<numVertexs) {
			throw new Exception("拓扑排序失败");
		}
	}
	
	public static void main(String[] args) {
		GraphTopologic graphTopologic=new GraphTopologic(14);
		graphTopologic.createGraph();
		try {
			graphTopologic.topologicalSort();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
