#include <windows.h>
#include <stdio.h>
#include <process.h>   //���߳���ص�ʱ�����Ҫ#include <process.h>�ṩ�������Զ��߳̽���֧�ֵĺ��������̵߳Ĵ������ս�û�ж��̹߳���ͻָ����в����ĺ����� 
#include <time.h>
#include <conio.h> 

unsigned _stdcall simulator(void *); //���庯�� 
unsigned _stdcall inspector(void *);//unsigned��ָ�����ķ���ֵ�������޷������� _stdcall��˵�����������Ǵ�������ͨ����ջ���ݵ�
LPVOID BASE_PTR; //�̺߳�����ָ�� 
int Actnum=0;  //ȫ�ֱ��� 


//����������Ҫ��������������ģ������ͽ��м�ص������̣߳�
int main(int argc, char* argv[])
{
	unsigned  ThreadID[2]; //�������� 
	_beginthreadex(NULL,0,simulator,NULL,0,&ThreadID[0]);   //���������³����C runtime���������������߳�ִ��ָ���Ŀ�ִ��ģ�顣
	_beginthreadex(NULL,0,inspector,NULL,0,&ThreadID[1]);
 //��1����������ȫ���ԣ�NULLΪĬ�ϰ�ȫ����
 //��2��������ָ���̶߳�ջ�Ĵ�С�����Ϊ0�����̶߳�ջ��С�ʹ��������̵߳���ͬ��һ����0
 //��3��������ָ���̺߳����ĵ�ַ��Ҳ�����̵߳���ִ�еĺ�����ַ(�ú������Ƽ��ɣ��������ƾͱ�ʾ��ַ)
 //��4�����������ݸ��̵߳Ĳ�����ָ�룬����ͨ����������ָ�룬���̺߳�������ת��Ϊ��Ӧ���ָ��
 //��5���������̳߳�ʼ״̬��0:�������У�CREATE_SUSPEND��suspended�����ң�
 //��6�����������ڼ�¼�߳�ID�ĵ�ַ
    getchar(); //����س�������ֹ����
	return 0;
	
} 

//ģ��һϵ�е��������Ϊһ���������߳����У�
unsigned _stdcall simulator(void *)
{
 	DWORD OldProtect; //����һ������ ��һ�����ڷ���ֵ�����и���������� 
 	int randnum;

 	printf("����ģ���������Ѿ�������\n");
 	//����һ����������ӣ�
 	srand( (unsigned)time( NULL ) );

 	//��һ����ѭ���У�����������ƣ����Ͻ������������
	while(1)
 	{
   		Sleep(500);//��������ģ��ͼ�ص��ٶȣ�
   		while (Actnum!=0)
   		{
    		Sleep(500);//�ȴ���ֱ������̲߳�׽����һ��ģ�⶯�����ټ�����һ��������
   		}
   		randnum=7&rand();
   		switch(randnum)//ע����������е����ָ���ʹ��BASE_PTR;���ڹ�������������
				 		//������̬����,���ĳ�������ɹ����򽫲��ᱻ����̼߳�ص���
   		{
     		case 0:
      			if (BASE_PTR=VirtualAlloc(NULL,1024*32,MEM_RESERVE|MEM_COMMIT,
							  PAGE_READWRITE))
	   			{
					Actnum=1;//���ı������ύ;
	   			}
	  		break;
     		case 1:
      			if (VirtualFree(BASE_PTR,1024*32,MEM_DECOMMIT))
      		 	{
	    			Actnum=2;//���ĳ���;
	   			}
	  		break;
     		case 2:
      			if (VirtualFree(BASE_PTR,0,MEM_RELEASE))
	   			{
	    			Actnum=3;//���ĳ��䲢�ͷ����ռ�;
	   			}
	  		break;
     		case 3:
      			if (VirtualProtect(BASE_PTR,1024*32,PAGE_READONLY,&OldProtect))//�ı������ڴ�ı�����ʽ��
       			{
	    			Actnum=4;
	   			}
	  		break;
     		case 4:
      			if (VirtualLock(BASE_PTR,1024*12))
       			{
	    			Actnum=5;//���������ڴ�ҳ;
	   			}
	  		break;
     		case 5:
      			if (BASE_PTR=VirtualAlloc(NULL,1024*32,MEM_RESERVE,PAGE_READWRITE)) 
				  //���������ύĳһ��Χ�������ַ������һ�����̱���һ�������ַʱ����û�������ڴ�ҳ���ύ�����ң�����һ����ַ��Χ�������п��õ������ڴ����ύ����Щ��ַ��Ҫʹ�ñ����ĵ�ַ���ڴ����ȱ��뱻�ύ���õ�ַ�����ڴ汻�ύʱ���ڴ�����ҳ�����䡣
	   			{
	    			Actnum=6;//���ı���;
	   			}
	  		break;
     		default:
	  		break;
   		}//end of 'switch'
 	}//end of 'while'
   return 0;
}


//ͨ��һ��ȫ�ֵı�����������һģ���̵߳�ģ������ͨ���ʵ�����Ϣ
//��ѯ���������洢��ʹ�úͻ���������棻
unsigned _stdcall inspector(void *)
{
int  QuOut=0;	
char para1[3000];
MEMORYSTATUS Vmeminfo;
char tempstr[100];
MEMORY_BASIC_INFORMATION inspectorinfo1; //
int structsize = sizeof(MEMORY_BASIC_INFORMATION);

printf("���ڼ�������ʼ����\n");
//��һ����ѭ���в���ͨ��һ��ȫ�ֱ��������������������ģ���߳��Ƿ�
//���µĶ���������У�ͨ����API��������Ӧ��洦(ͨ������BASE_PTR
//ʵ��)����Ϣ���м�飬�Ӷ���֤�ö����Դ洢ʹ�õ�Ӱ�죻
while(1)
{
 Sleep(1000); //ʹ��ǰ�߳����ߡ�
 if(Actnum!=0)
 {
  //ͨ��ȫ�ֱ�������������Actnum,����ȡ��һ����涯��������
  //����Ӧ��������Ϣ��ͷ����
  switch(Actnum)
  {
   case 1:
    memset (&inspectorinfo1, 0, structsize);//buffer��Ϊָ���������,c���Ǹ���buffer��ֵ,count����buffer�ĳ���.
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize); //��ѯһ�����̵������ڴ�
    strcpy(para1,"Ŀǰִ�ж��������ı������ύ\n");
    break;
   case 2:
    memset (&inspectorinfo1, 0, structsize);
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize);
    strcpy(para1,"Ŀǰִ�ж��������ĳ���\n");
    break;
   case 3:
    memset (&inspectorinfo1, 0, structsize);
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize);
    strcpy(para1,"Ŀǰִ�ж��������ĳ��䲢�ͷ����ռ�\n");
    break;
   case 4:
    memset (&inspectorinfo1, 0, structsize);
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize);
    strcpy(para1,"Ŀǰִ�ж������ı������ڴ�ҳ�ı���\n");
    break;
   case 5:
    memset (&inspectorinfo1, 0, structsize);
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize);
    strcpy(para1,"Ŀǰִ�ж��������������ڴ�ҳ\n");
    break;
   case 6:
    memset (&inspectorinfo1, 0, structsize);
    VirtualQuery((LPVOID)BASE_PTR,&inspectorinfo1,structsize);
    strcpy(para1,"Ŀǰִ�ж��������ı���\n");
    break;
   default: 
    break;
  }

  //ʵʱ��ʾ�̶���ʽ����ز��ϣ�ͨ��Ŀǰ��ص��Ķ���������
  //������ַ����ظû����Ӧ�洢�ռ��Ӱ�죻
  sprintf(tempstr,"��ʼ��ַ��0X%x\n",inspectorinfo1.BaseAddress);
  strcat(para1,tempstr); 
  sprintf(tempstr,"�����С(Ŀǰָ�봦��ǰͬһ���ԵĿ�)��0X%x\n",
		inspectorinfo1.RegionSize);
  strcat(para1,tempstr);
  sprintf(tempstr,"Ŀǰ״̬��0X%x\n",inspectorinfo1.State);
  strcat(para1,tempstr);
  sprintf(tempstr,"����ʱ���ʱ���:0X%x\n",inspectorinfo1.AllocationProtect);
  strcat(para1,tempstr);
  sprintf(tempstr,"��ǰ���ʱ���:0X%x\n",inspectorinfo1.Protect);
  strcat(para1,tempstr);
  strcat(para1,"(״̬:10000����δ���� ;1000�����ύ ;2000������; )\n");
  strcat(para1,"(������ʽ:0��������;1�����ֹ����;2����ֻ��;4�����д;\n10�����ִ");
  strcat(para1," ��;20����ɶ���ִ��; 40����ɶ�д��ִ��;)\n*******************\n");

  //ȫ����Ϣ������Ŀǰϵͳ�͵�ǰ���̵Ĵ洢ʹ�����������
  GlobalMemoryStatus(&Vmeminfo); //�ú������ڻ�ȡ����洢�ռ��ʹ��״���Լ�ϵͳ��ʹ�øſ���
  strcat(para1,"��ǰ����洢ͳ������\n");
  sprintf(tempstr,"�����ڴ�������%d(BYTES)\n",Vmeminfo.dwTotalPhys);
  strcat(para1,tempstr); //trcat���������������ַ���
  sprintf(tempstr,"���������ڴ�����%d(BYTES)\n",Vmeminfo.dwAvailPhys);
  strcat(para1,tempstr);
  sprintf(tempstr,"ҳ���ļ�������%d(BYTES)\n",Vmeminfo.dwTotalPageFile);
  strcat(para1,tempstr);
  sprintf(tempstr,"����ҳ���ļ�����%d(BYTES)\n",Vmeminfo.dwAvailPageFile);
  strcat(para1,tempstr);
  sprintf(tempstr,"���ռ�������%d(BYTES)\n",Vmeminfo.dwTotalVirtual);
  strcat(para1,tempstr);
  sprintf(tempstr,"�������ռ�����%d(BYTES)\n",Vmeminfo.dwAvailVirtual);
  strcat(para1,tempstr);
  sprintf(tempstr,"����洢ʹ�ø���:%%%d\n\n\n\n",Vmeminfo.dwMemoryLoad);
  strcat(para1,tempstr);//trcat���������������ַ���
  printf("%s",para1);//��ʾ��������
  //(�������ͬʱ���������ݼ�¼����־�ļ�)��
  Actnum=0;//֪ͨģ���߳̿��Խ�����һ��ģ�⶯����
  Sleep(500);//����ģ��ͼ�ص������ٶȣ�
  }//for if 
}//for while

return 0;
}

