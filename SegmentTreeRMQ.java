import org.checkerframework.checker.nullness.qual.*;
import java.util.*;
import java.io.*;
class SegmentTreeRMQ
{
    @Nullable
    pair st[]=new pair[4]; //array to store segment tree
    // A utility function to get minimum of two numbers
    pair minVal(pair x, pair y) {
        return (x.a < y.a) ? x : y;
    }
 
    // A utility function to get the middle index from corner
    // indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }
 
    /*  A recursive function to get the minimum value in a given
        range of array indexes. The following are parameters for
        this function.
 
        st    --> Pointer to segment tree
        index --> Index of current node in the segment tree. Initially
                   0 is passed as root is always at index 0
        ss & se  --> Starting and ending indexes of the segment
                     represented by current node, i.e., st[index]
        qs & qe  --> Starting and ending indexes of query range */
     @Nullable pair RMQUtil(int ss, int se, int qs, int qe, int index)
    {
        // If segment of this node is a part of given range, then
        // return the min of the segment
        if (qs <= ss && qe >= se)
            return st[index];
 
        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return new pair(Integer.MAX_VALUE,0);
 
        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }
 
    // Return minimum of elements in range from index qs (quey
    // start) to qe (query end).  It mainly uses RMQUtil()
    pair RMQ(int n, int qs, int qe)
    {
        // Check for erroneous input values
        
 
        return RMQUtil(0, n - 1, qs, qe, 0);
    }
 
    // A recursive function that constructs Segment Tree for
    // array[ss..se]. si is index of current node in segment tree st
    pair constructSTUtil(int arr[], int ss, int se, int si)
    {
        // If there is one element in array, store it in current
        //  node of segment tree and return
        if (ss == se) {
    
            if(st[si]!=null)
            {
                st[si].a = arr[ss];
                if(st[si]!=null)
                {
                  st[si].b = ss;    
                }
                    
            }

            
            return st[si];
        }
 
        // If there are more than one elements, then recur for left and
        // right subtrees and store the minimum of two values in this node
        int mid = getMid(ss, se);
        st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1),
                constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }
 
    /* Function to construct segment tree from given array. This function
       allocates memory for segment tree and calls constructSTUtil() to
       fill the allocated memory */
    void constructST(int arr[], int n)
    {
        // Allocate memory for segment tree
 
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
 
        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new pair[max_size]; // allocate memory
        for(int i=0; i<max_size; i++)
        	st[i]=new pair();
        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, 0);
    }
 
}class SegmentTreeRMaxQ
{
    @Nullable
    pair st[]=new pair[4]; //array to store segment tree
    // A utility function to get minimum of two numbers
     pair minVal(pair x, pair y) {
        return (x.a > y.a) ? x : y;
    }
 
    // A utility function to get the middle index from corner
    // indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }
 
    /*  A recursive function to get the minimum value in a given
        range of array indexes. The following are parameters for
        this function.
 
        st    --> Pointer to segment tree
        index --> Index of current node in the segment tree. Initially
                   0 is passed as root is always at index 0
        ss & se  --> Starting and ending indexes of the segment
                     represented by current node, i.e., st[index]
        qs & qe  --> Starting and ending indexes of query range */
    @Nullable  pair RMQUtil(int ss, int se, int qs, int qe, int index)
    {
        // If segment of this node is a part of given range, then
        // return the min of the segment
        if (qs <= ss && qe >= se)
            return st[index];
 
        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return new pair(0,0);
 
        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }
 
    // Return minimum of elements in range from index qs (quey
    // start) to qe (query end).  It mainly uses RMQUtil()
    pair RMQ(int n, int qs, int qe)
    {
        // Check for erroneous input values
        
 
        return RMQUtil(0, n - 1, qs, qe, 0);
    }
 
    // A recursive function that constructs Segment Tree for
    // array[ss..se]. si is index of current node in segment tree st
    @Nullable pair constructSTUtil(int arr[], int ss, int se, int si)
    {
        // If there is one element in array, store it in current
        //  node of segment tree and return
        if (ss == se) {

            if(st[si]!=null)
            {
                st[si].a = arr[ss];
                if(st[si]!=null)
                {
                    st[si].b = ss;    
                }
                    
            }
            
            return st[si];
        }
 
        // If there are more than one elements, then recur for left and
        // right subtrees and store the minimum of two values in this node
        int mid = getMid(ss, se);
        st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1),
                constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }
 
    /* Function to construct segment tree from given array. This function
       allocates memory for segment tree and calls constructSTUtil() to
       fill the allocated memory */
    void constructST(int arr[], int n)
    {
        // Allocate memory for segment tree
 
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
 
        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new pair[max_size]; // allocate memory
        for(int i=0; i<max_size; i++)
        	st[i]=new pair();
        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, 0);
    }
 
}
//segment tree code from geeksforgeeks
class pair
{
	pair(int c,int d)
	{
		a=c;
		b=d;
	}
	pair()
	{
		
	}
	int a,b;
}
class q1
{
	@Nullable static SegmentTreeRMaxQ a;
	@Nullable static SegmentTreeRMQ b;
	@Nullable static List<pair> c;
    @Nullable static pair te;
    @Nullable static pair te1;
	static int n;
	public static void main(String[] args)
	{
		int t=ni();
		ou:while(t-->0)
		{
			n=ni();
			int[] cur=nia(n);
			int[] fin=nia(n);
			a=new SegmentTreeRMaxQ();
			b=new SegmentTreeRMQ();
			if(a!=null)
            {
                a.constructST(fin, n);    
            }
            
			if(b!=null)
            {
                b.constructST(cur, n);    
            }
            
			int cou=0;
			c=new ArrayList<pair>();
			for(int i=0; i<n; i++)
			{
				if(fin[i]>cur[i])
				{
					sop(-1);
					continue ou;
				}
				else if(cur[i]>fin[i])
				{
					cou++;
					c.add(new pair(fin[i],i));
				}
			}
			Collections.sort(c, new Comparator<pair>()
			{
				@Override
				public int compare(pair a,pair b)
				{
					if(a.a>b.a)
						return 1;
					if(a.a<b.a)
						return -1;
					if(a.b>b.b)
						return 1;
					if(a.b<b.b)
						return -1;
					return 0;
				}
			});
			for(int i=c.size()-1; i>=0; i--)
			{
				int j=i;
                if(c!=null)
                {
                    while(j!=0&&c.get(i).a==c.get(j-1).a)
                    j--;
                    cou-=pro(j,i);
                    i=j;    
                }
				
			}
			sop(cou);
		}
	}
	static int pro(int i, int j)
	{
		if(i==j)
			return 0;
//		sop(i+" "+j);
        

        if(a!=null && c!=null)
        {
             te=a.RMQ(n, c.get(i).b, c.get(j).b);    
        }
		
        if (te!=null && te1!=null && c!=null && a!=null)
        {
            if(te.a<=c.get(i).a)
        {
            if(b!=null)
            {
                 te1=b.RMQ(n, c.get(i).b, c.get(j).b);    
            }
            
            if(c!=null)
            {
                if(te1.a>=c.get(i).a)
            {
                return j-i;
            }
            else {
                int mid = binaysearcher(i, j, te1.b);
                return (pro(i, mid-1)+pro(mid, j));
            }
                
            }
            else
            {
                return 0;
            }
            
        }
        else {
            int mid = binaysearcher(i, j, te.b);
            return (pro(i, mid-1)+pro(mid, j));
        }
        }
        else 
        {
            return 0 ;
        }
		
	}
	static int binaysearcher(int start, int end, int value) {
		while(start < end)
		{
			int mid = (start+end)/2;
			if(c!=null)
            {
                if(c.get(mid).b > value) {
                end = mid;
            }
                else if(c.get(mid).b< value) {
                start = mid+1;
            }

            }
            
		}
		return end;
	}
	static final long mod=1000000007;
	static final double eps=1e-8;
	static final long inf=100000000000000000L;
	static final boolean debug=false;
	static Scanner in=new Scanner(System.in);
	static StringBuilder ans=new StringBuilder();
	static long powm(long a, long b, long m)
	{
		long an=1;
		long c=a;
		while(b>0)
		{
			if(b%2==1)
				an=(an*c)%m;
			c=(c*c)%m;
			b>>=1;
		}
		return an;
	}
	static Random rn=new Random();
	static void pr(Object a){ans.append(a+"\n");}
	static void pr(){ans.append("\n");}
	static void p(Object a){ans.append(a);}
	static void sop(Object a){System.out.println(a);}
	static int ni(){return in.nextInt();}
	static int[] nia(int n){int a[]=new int[n];for(int i=0; i<n; i++)a[i]=ni();return a;}
	static int[] ria(int n){int a[]=new int[n];for(int i=0; i<n; i++){a[i]=rn.nextInt(100);p(a[i]+" ");}pr();return a;}
	static long[] nla(int n){long a[]=new long[n];for(int i=0; i<n; i++)a[i]=nl();return a;}
	static String[] nsa(int n){String a[]=new String[n];for(int i=0; i<n; i++)a[i]=ns();return a;}
	static long nl(){return in.nextLong();}
	static String ns(){return in.next();}
	static double nd(){return in.nextDouble();}
	static String pr(String a, long b)
	{
		String c="";
		while(b>0)
		{
			if(b%2==1)
				c=c.concat(a);
			a=a.concat(a);
			b>>=1;
		}
		return c;
	}
	

}
