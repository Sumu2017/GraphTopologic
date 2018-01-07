package com.sumu.demo;

import java.util.Stack;

import com.sumu.demo.GraphTopologic.VertexNode;

/**
 * ��������
 * @author YaoMingliang
 * @date  2018��1��7��  ����11:32:35
 */
public class GraphTopologic {
	//�߱���
	class EdgeNode{
		private int adjVert;//�±��
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
	//�ڽӶ���
	class VertexNode{
		private int in;//�������
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
	private VertexNode[] adjList;//�ڽӶ����һ������
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
	 * 1�������ҵ����Ϊ0�Ķ��㣬����ѹ��ջ�У�ջ����������Ϊ�㣬����ִ�еĶ��㣩
	 * 2��Ȼ���ջ��ȡ��һ����ִ�ж��㣬�����鿴�ö�������г��ȶ���(�Ƚ����еĳ��ȶ����������1���ж��Ƿ�Ϊ0)��
	 * ������ȶ���������Ϊ�㣬��˵���ó��ȶ������ȶ��㶼��ִ���꣬˵���ö������ִ�У��ͽ���ѹ��ջ�У�ֱ��ջ��Ϊֹ
	 * 
	 * ����˼�룺���ж���ִ�ж���һ�����Ⱥ�˳��������ÿ������ִ��֮ǰ���豣֤����ȶ����Ѿ�ִ���ꡣ
	 * @throws Exception
	 */
	private void topologicalSort() throws Exception {
		int count = 0;
		Stack<Integer> vertexNodes=new Stack<>();
		for (int i = 0; i < numVertexs; i++) {
			if (adjList[i].in==0) {
				//Ѱ�����Ϊ0�ģ�����ѹ��ջ
				vertexNodes.push(i);
			}
		}
		
		while (!vertexNodes.isEmpty()) {
			int pop = vertexNodes.pop();
			System.out.println("����:"+adjList[pop].data);
			count++;
			for (EdgeNode edgeNode=adjList[pop].firstNode; edgeNode!=null; edgeNode=edgeNode.next) {
				int adjVert = edgeNode.adjVert;
				adjList[adjVert].in--;
				if (adjList[adjVert].in==0) {
					vertexNodes.push(adjVert);//���Ϊ0,��ջ
				}
			}
		}
		if (count<numVertexs) {
			throw new Exception("��������ʧ��");
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
