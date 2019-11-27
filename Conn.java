public int COUNT_CONNECTED_COMPONENTS()
    {
        return component_total;
        int t = 0;
        boolean [] visited_triangles = new boolean[index_triangle];
        boolean [] visited_edges = new boolean[index_edge];
        for (int i = 0; i < index_triangle; i++)
        {
            Triangle tr = triangles.get(i);
            if (!visited_triangles[tr.index])
            {
                Queue<Triangle> q = new Queue<>();
                q.offer(tr);
                visited_triangles[tr.index] = true;
                //System.out.println(tr.index);
                Edge e;
                Triangle tr1;

                while(!q.isEmpty())
                {
                    tr = q.poll();
                    for (int j = 0; j < 3; j++)
                    {
                        e = tr.edges[j];
                        if (!visited_edges[e.index])
                        {
                            visited_edges[e.index] = true;
                            for (int k = 0; k < e.triangles.size(); k++)
                            {
                                visited_edges[e.index] = true;
                               tr1 = e.triangles.get(k);
                                if (!visited_triangles[tr1.index])
                                {
                                    visited_triangles[tr1.index] = true;
                                    q.offer(tr1);
                                }
                            }
                        }
                    }
                }
                t++;
            }
        }
    }

