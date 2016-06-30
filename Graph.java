/**
 * Created by Vitalik on 24.06.2016.
 */

import javax.swing.*;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Graph extends JFrame
{
    File Prim_input_File = null;
    File Kraskal_input_File = null;
    File Boruvki_input_File = null;

    int [][] graph;
    int size_graph;

    ArrayList<Integer> initU(int size)
    {
        ArrayList<Integer> U = new ArrayList<>();
        for(int i = 0; i < size; i++)
            U.add(i);
        return U;
    }

    Graph(String s)
    {
        super("Форма");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel forma = new JPanel();
        forma.setLayout(new BorderLayout());

        JPanel left_panel = new JPanel();
        JPanel right_panel = new JPanel();

        JPanel prim_panel = new JPanel();
        JButton button_1 = new JButton("Прим");
        prim_panel.add(button_1);
        button_1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Graph g = new Graph("src\\input.txt");
                Prim p = new Prim(g);
                p.Prim();
                p.Print_Tree();
            }
        });

        JPanel kraskal_panel = new JPanel();
        JButton button_2 = new JButton("Краскал");
        kraskal_panel.add(button_2);

        button_2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Graph g = new Graph("src\\kruskal.txt");
                Kruskal p = new Kruskal(size_graph, graph);
                p.Kruskal();
                p.Print_Tree();
            }
        });

        JPanel boruvki_panel = new JPanel();
        JButton button_3 = new JButton("Борувки");
        boruvki_panel.add(button_3);

        button_3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Graph g = new Graph("src\\boruvka.txt");
                Boruvka p = new Boruvka(size_graph, graph);
                p.Boruvka();
                p.Print_Tree();
            }
        });

        prim_panel.setLayout(new BoxLayout(prim_panel, BoxLayout.Y_AXIS));
        kraskal_panel.setLayout(new BoxLayout(kraskal_panel, BoxLayout.Y_AXIS));
        forma.add(left_panel, BorderLayout.WEST);
        forma.add(right_panel, BorderLayout.EAST);
        forma.add(boruvki_panel, BorderLayout.CENTER);

        left_panel.add(prim_panel);
        right_panel.add(kraskal_panel);

        JLabel result_label = new JLabel("");

        JPanel result_panel = new JPanel();
        result_panel.add(result_label);

        forma.add(result_panel, BorderLayout.SOUTH);

        add(forma);

        setSize(360, 130);
        setLocationRelativeTo(null);
        setVisible(true);

        try (Scanner sc = new Scanner(new FileReader(s)))
        {
            size_graph = sc.nextInt();
            initU(size_graph);
            graph = new int [size_graph][size_graph];
            for (int i = 0; i < size_graph; i++)
                for(int j = 0; j < size_graph; j++)
                    graph[i][j] = sc.nextInt();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    void Print_Matrix()
    {
        for(int i = 0; i < size_graph; i++)
        {
            for(int j = 0; j < size_graph; j++)
            {
                System.out.print(graph[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        Graph g = new Graph("src\\boruvka.txt");
        Boruvka p = new Boruvka(g.size_graph, g.graph);
        p.Boruvka();
        p.Print_Tree();
    }
}

