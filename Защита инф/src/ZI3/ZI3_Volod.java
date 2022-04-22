package ZI3;

import java.util.Scanner;

public class ZI3_Volod {
    public static void main(String[] args)
    {
//int Maxst1=8;
//int Maxst2=12;
        int k=0; //счетчик шагов
        int Maxst1=4;
        int[] A = { 1, 1, 1, 0, 1};
        String AS="";
        @SuppressWarnings("resource")
        Scanner inp = new Scanner(System.in);
        System.out.println("Введите максимальную степень делимого = ");
        int Maxst2 = inp.nextInt();
        int[] B=  new int[Maxst2+1];
        String BS="";

        for (int i = 0; i<Maxst2+1; i++)
        {
            System.out.print("x^"+(Maxst2-i)+"=");
            B[i]=inp.nextInt();

        }
        int Maxst3=Maxst2;
        int C [][]=new int[Maxst3+1] [3];

        //вывод массивов для проверки
        System.out.println("Делимое");
        for (int i = 0; i <Maxst2+1; i++) {
            System.out.println(B[i]+" ");
            C[i][0]=B[i];
            BS+=B[i];
        }
        System.out.println();
        int otvdl=0;
        System.out.println("Делитель");
        for (int i = 0; i <Maxst1+1; i++) {
            System.out.println(A[i]+" ");
            C[i][1]=A[i];
            AS+=A[i];
        }
        System.out.println();
        while (Integer.parseInt(BS) >=Integer.parseInt(AS)) {
            System.out.println("Шаг "+(k+1));
            k++;
            otvdl=Maxst3;
            for (int i = 0; i <Maxst3+1; i++) {

                if (C[i][0]!=C[i][1])
                    C[i][2]=1;
                else
                    C[i][2]=0;
                System.out.println("Степень "+(Maxst3-i)+" "+C[i][0]+" : "+C[i][1]+" = "+C[i][2]);

            }
            for (int i = 0; i <Maxst3+1; i++) {
                if (C[i][2]>0)
                {
                    int isdvig=i; //передвижение, если при бОльших степенях стоят нули
                    Maxst3=Maxst3-i;
                    BS="";
                    //	Console.WriteLine(isdvig);
                    for (int j = 0; j <Maxst3+1; j++)
                    {
                        C[j][0]=C[j+isdvig][2];
                        C[j][1]=C[j][1];
                        BS+=C[j+isdvig][2];
                    }
                    break;
                    //тогда выходим из цикла
                }
                System.out.println();
            }
        }
        //вывод результата
        String s="Конечный результат: Исходный полином: ";
        for (int i = 0; i <Maxst2+1; i++) {
            if (B[i]>0)
                s+=(B[i]+"*x^"+(Maxst2-i)+" + ");
        }
        String s1="Делитель: ";
        for (int i = 0; i <Maxst1+1; i++) {
            if (A[i]>0)
                s1+=(A[i]+"*x^"+(Maxst1-i)+" + ");
        }

        String s2="Остаток от деления: ";
        for (int i = 0; i <otvdl+1; i++)  {
            if (C[i][2]>0)
                s2+=(C[i][2]+"*x^"+(otvdl-i)+" + ");
        }

        s=s.substring(0,s.length()-2);
        s1=s1.substring(0,s1.length()-2);
        s2=s2.substring(0,s2.length()-2);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
    }
}
