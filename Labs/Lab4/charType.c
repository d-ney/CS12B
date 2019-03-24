/*
 * Lab 4
 * Dillon Ney
 * ID # 1705097
 */



#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* l, char* a, char* w, char* p, char* n);
void printToOut(char* a, char* w, char* p, char* n, int lc, FILE* out);


int main(int argc, char* argv[]){
   FILE* in;        // handle for input file
   FILE* out;       // handle for output file
   char* line;      // string holding input line
   char* alpha; // string holding all alpha chars
   char* num;	// string holding all numeric chars
   char* white;	// string holding all whitespace chars
   char* punc;	// string holding all punctuation chars
   int lc = 0;

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap
      line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
      alpha= calloc(MAX_STRING_LENGTH+1, sizeof(char) );
      num= calloc(MAX_STRING_LENGTH+1, sizeof(char) );
      white= calloc(MAX_STRING_LENGTH+1, sizeof(char) );
      punc= calloc(MAX_STRING_LENGTH+1, sizeof(char) );


      assert( line!=NULL && alpha!=NULL );

      // read each line in input file, extract alpha-numeric characters
      while( fgets(line, MAX_STRING_LENGTH, (FILE*) in) != NULL ){
    	  extract_chars(line, alpha, white, punc, num);
         lc ++;
         printToOut(alpha, white, punc, num, lc, out);
      }

      // free heap memory
      free(line);
      free(alpha);
      free(white);
      free(punc);
      free(num);
      // close input and output files
      fclose(in);
      fclose(out);

      return EXIT_SUCCESS;
}

void extract_chars(char* l, char* a, char* w, char* p, char* n)
{
	//printf(l);
	int wi = 0;
	int ai = 0;
	int pi = 0;
	int ni = 0;

	for(int i = 0; i < strlen(l); i++)
	{
		if(l[i] <= 32)
		{
			w[wi] = l[i];
			wi++;
		}
		else if(l[i] >= 48 && l[i] <= 57)
		{
			n[ni] = l[i];
			ni++;
		}
		else if((l[i] >= 65 && l[i] <= 90) || (l[i] >= 97 && l[i] <= 122))
		{
			a[ai] = l[i];
			ai++;
		}
		else
		{
			p[pi] = l[i];
			pi++;
		}


	}
	a += '\0';
	w += '\0';
	p += '\0';
	n += '\0';
	memset(&l[0], 0, strlen(l));
}

void printToOut(char* a, char* w, char* p, char* n, int lc, FILE* out)
{
	int alen = strlen(a);
	int nlen = strlen(n);
	int plen = strlen(p);
	int wlen = strlen(w);

	char* id = '\0';
	char id2 = '\0';
	fprintf(out, "Line %d contains: \n", lc);

	for(int i = 0; i < 4; i++)
	{
		id2 = '\0';
		if(i == 0)
		{
			id = "alphabetic";
			if(alen > 1)
				id2 = 's';
			fprintf(out,"%d %s character%c: %s\n", alen, id, id2, a);
		}
		if(i == 1)
		{
			id = "numeric";
			if(nlen >1)
				id2 = 's';
			fprintf(out,"%d %s character%c: %s\n", nlen, id, id2, n);
		}
		if(i == 2)
		{
			id = "punctuation";
			if(plen > 1)
				id2 = 's';
			fprintf(out,"%d %s character%c: %s\n", plen, id, id2, p);
		}
		if(i == 3)
		{
			id = "whitespace";
			if(wlen != 1)
				id2 = 's';
			fprintf(out,"%d %s character%c: %s\n", wlen, id, id2, w);
		id2 ='\0';
		}

//		fprintf(out,"%d %s character%s: %s\n"
//					"%d %s character%s: %s\n"
//					"%d %s character%s: %s\n"
//					"%d %s character%s: %s\n", alen, id, id2, a, nlen, id, id2, n, plen, id, id2,  p, wlen, id, id2, w);
	}





	memset(&a[0], 0, strlen(a));
	memset(&w[0], 0, strlen(w));
	memset(&p[0], 0, strlen(p));
	memset(&n[0], 0, strlen(n));
}
