#include <stdio.h>
#include <stdlib.h>
#include "main.h"
#include <time.h>
#include <locale.h>
#include <string.h>
#define tamanho_ficha 50


// VARIAVEIS GLOBAIS

int numeroCadastrados = 0;

typedef struct{
	int dia;
	int mes;

} DATA_VENCIMENTO;


typedef struct{
	int dia;
	int mes;
	int ano;

} DATA_NASCIMENTO;



typedef struct{
	char NOME_DEPENDENTE[15];
	char CPF_DEPENDENTE[11];
	DATA_NASCIMENTO dataNascimentoDP;


} DEPENDENTE;

typedef struct{
	
	char nome[15];
	char sobrenome[20];
	
	char cpf[11];
	int sexo;
	char email[60];
	DATA_NASCIMENTO dataNascimento;
	char telefone[12];
	int idade;


	// DADOS DEPENDENTE
	DEPENDENTE dependentes[2];
	//DENTRO DE DEPENDENTES TEM ISSO: 
	//dependente.NOME_DEPENDENTE;
	//dependente.CPF_DEPENDENTE;
	//dependente.dataNascimentoDP;
	int qtdDependente;

	//DADOS PLANO
	float valorPlano;
	DATA_VENCIMENTO DatavencimentoPlano; 
	int ativo;


	//TIPO E NOME DO PLANO
	int tipoDoPlano;
	char nomeDoPlano[9];

} REGISTRO;

REGISTRO fichas[tamanho_ficha];

void imprimeMenuSeguro(){
	// EU SEI QUE TEM UMA BARRINHA ERRADA, MAS AO COMPILAR ELA FICA CERTA!!!!! 
	printf("-----------------------------------------------------\n");
	printf("TIPO DO PLANO        |     VALOR BASE PARA O SEGURO\n");
	printf("OURO 		     | 			RS300,00\n");
	printf("DIAMANTE	     |			RS400,00\n");
	printf("PRATA		     |			RS200,00\n");
	printf("ESMERALDA	     |	    	        RS500,00\n");
	printf("-----------------------------------------------------\n");
	printf("[1] - OURO / [2] - DIAMANTE / [3] - PRATA / [4] - ESMERALDA\n");
}

void imprimeMenu(){
	printf(" 1 - Cadastrar Cliente:\n");
    printf(" 2 - Editar Cliente:\n");
    printf(" 3 - Remover Cliente:\n");
    printf(" 4 - Listagem Geral:\n");
    printf(" 5 - Listagem por Plano:\n");
    printf(" 6 - Listagem dos Vencimentos do Plano de Saude do mes\n");
    printf(" 0 - sair\n");
}

//CREATE
void adicionaValorPlano(int opcao, int pos){
	
	fichas[pos].tipoDoPlano = opcao;

	if(opcao == 1){	
		fichas[pos].valorPlano = 300.00;
	} 

	if(opcao == 2) {
		fichas[pos].valorPlano = 400.0;
	}	

	if(opcao == 3){
		fichas[pos].valorPlano = 200.0;
	}

	if(opcao == 4){
		fichas[pos].valorPlano = 500.0;
	}

	adiciona_nome_do_plano(pos, opcao);
	verifica_sexo_e_idade(pos);
}

void adiciona_nome_do_plano(int pos, int opcao){

	switch(opcao){
		case 1:
			sprintf(fichas[pos].nomeDoPlano, "OURO"); 
			break;
		case 2:
			sprintf(fichas[pos].nomeDoPlano, "DIAMANTE"); 
			break;
		case 3:
			sprintf(fichas[pos].nomeDoPlano, "PRATA"); 
			break;
		case 4:
			sprintf(fichas[pos].nomeDoPlano, "ESMERALDA"); 
			break;
	}

}

void recebeDataNascimento(){
	printf("INSIRA SUA DATA DE NASCIMENTO ABAIXO:\n");
	recebeDataNascimentoDIA();
	recebeDataNascimentoMES();
	recebeDataNascimentoANO();
	cadastraIdade(numeroCadastrados);
}

void recebeDataNascimentoDIA(){
	int dia;
	
	
	do{
		printf("DIA:\n");	
		scanf("%d", &dia);
		
		if(dia < 1 || dia > 31){
			printf("DIGITE UMA DATA ENTRE 01 A 31!\n");
		}

	} while(dia < 1 || dia > 31);

	fichas[numeroCadastrados].dataNascimento.dia = dia;


}

void recebeDataNascimentoMES(){
	int mes;
	
	
	do{
		printf("MES:\n");	
		scanf("%d", &mes);
		
		if(mes < 1 || mes > 12){
			printf("DIGITE UM MES VALIDO!\n");
		}

	} while(mes < 1 || mes > 12);

	fichas[numeroCadastrados].dataNascimento.mes = mes;


}


void recebeDataNascimentoANO(){
	
	
	time_t mytime;
	mytime = time(NULL);
    struct tm tm = *localtime(&mytime);
	
    int ano_atual = tm.tm_year + 1900;
	int ano;


	do{
		printf("ANO:\n");	
		scanf("%d", &ano);
		
		if(ano < 1900 || ano > ano_atual){
			printf("DIGITE UM ANO ENTRE 1900 E %d!\n", ano_atual);
		}

	} while(ano < 1900 || ano > ano_atual);

	fichas[numeroCadastrados].dataNascimento.ano = ano;


}

void cadastraIdade(int pos){
	time_t mytime;
	mytime = time(NULL);
    struct tm tm = *localtime(&mytime);
    
    
    //RECEBENDO DATA ATUAL
    int diaAtual = tm.tm_mday;
    int mesAtual = tm.tm_mon;
    int anoAtual = tm.tm_year + 1900;

    int ano_atual_menos_ano_nasc = anoAtual - fichas[pos].dataNascimento.ano;

    if(mesAtual < fichas[pos].dataNascimento.mes) ano_atual_menos_ano_nasc--;

    fichas[pos].idade = ano_atual_menos_ano_nasc;

}

void recebeoSexo(int pos){
	do{
		printf("SEXO: [1- FEM/2-MASC]\n");
		int opcaoSexo;
		scanf("%d", &opcaoSexo);
	

		if(opcaoSexo == 1 || opcaoSexo == 2) {
			fichas[pos].sexo = opcaoSexo;
			break;
		}

		printf("DIGITO INVALIDO!\n");
		


		} while(1);

}

void cadastraDependente(int pos_do_dependente){
	
	printf("NOME:\n");
	fflush(stdin);
	gets(fichas[numeroCadastrados].dependentes[pos_do_dependente].NOME_DEPENDENTE);



	printf("CPF:\n");
	fflush(stdin);
	gets(fichas[numeroCadastrados].dependentes[pos_do_dependente].CPF_DEPENDENTE);


	recebeDataNascimento_DoDependente(pos_do_dependente);
	
	fichas[numeroCadastrados].qtdDependente++;

	if(fichas[numeroCadastrados].qtdDependente == 1) cadastraMaisUm(fichas[numeroCadastrados].qtdDependente);
	


}
	

void cadastraMaisUm(int posicao){


	printf("DESEJA CADASTRAR MAIS UM DEPENDENTE? [1] - SIM / [2] - NAO\n");
	
	do{
		printf("OPCAO:\n");
		int opcaoNovoDependente;
		scanf("%d", &opcaoNovoDependente);
	
	
		if(opcaoNovoDependente == 1){
			cadastraDependente(posicao);
			break;
			}
	
			if(opcaoNovoDependente == 2) break;
			printf("OPCAO INVALIDA!\n");
	
	}while(1);

}

void recebeDataNascimento_DoDependente(int pos){
	// fazer validacao de data

	recebeDataNascimentoDependenteDIA(pos);
	recebeDataNascimentoDependenteMES(pos);
	recebeDataNascimentoDependenteANO(pos);

}

void recebeDataNascimentoDependenteDIA(int pos){
	
	int dia;
	
	
	do{
		printf("DIA:\n");	
		scanf("%d", &dia);
		
		if(dia < 1 || dia > 31){
			printf("DIGITE UMA DATA ENTRE 01 A 31!\n");
		}

	} while(dia < 1 || dia > 31);

	fichas[numeroCadastrados].dependentes[pos].dataNascimentoDP.dia = dia;

}



void recebeDataNascimentoDependenteMES(int pos){
	int mes;
	
	
	do{
		printf("MES:\n");	
		scanf("%d", &mes);
		
		if(mes < 1 || mes > 12){
			printf("DIGITE UM MES VALIDO!\n");
		}

	} while(mes < 1 || mes > 12);

	fichas[numeroCadastrados].dependentes[pos].dataNascimentoDP.mes = mes;



}

void recebeDataNascimentoDependenteANO(int pos){

	time_t mytime;
	mytime = time(NULL);
    struct tm tm = *localtime(&mytime);
	
    int ano_atual = tm.tm_year + 1900;
	int ano;


	do{
		printf("ANO:\n");	
		scanf("%d", &ano);
		
		if(ano < 1900 || ano > ano_atual){
			printf("DIGITE UM ANO ENTRE 1900 E %d!\n", ano_atual);
		}

	} while(ano < 1900 || ano > ano_atual);

	fichas[numeroCadastrados].dependentes[pos].dataNascimentoDP.ano = ano;



}


void verifica_e_cadastra_DEPENDENTE(){
	do{

		printf("VOCE POSSUI DEPENDENTES? [1] - SIM / [2] - NAO\n");
		printf("OPCAO:");
		int opcaoDependentes;	
		scanf("%d", &opcaoDependentes);
		if(opcaoDependentes == 1){
			fichas[numeroCadastrados].qtdDependente = 0;
			cadastraDependente(fichas[numeroCadastrados].qtdDependente);
			descontoNoPlano(20, numeroCadastrados);
			break;
		}

		if(opcaoDependentes == 2) break;
		printf("OPCAO INVALIDA!\n");

	} while(1);

}

void verifica_sexo_e_idade(int pos){
	//SEXO FEMININO = 1
	//SEXO MASCULINO = 2
	
	if(fichas[pos].sexo == 1){
		if(fichas[pos].idade >= 13 && fichas[pos].idade <= 35){
			acrescimoNoPlano(30, pos);
		}
	}
}

void gera_data_vencimento(){
	
	time_t mytime;
	mytime = time(NULL);
    struct tm tm = *localtime(&mytime);
    

	srand(time(NULL));

	int geraDia = (rand() % 30) + 0001;
	
	int geraMes = tm.tm_mon;

	
	fichas[numeroCadastrados].DatavencimentoPlano.dia = geraDia;
	fichas[numeroCadastrados].DatavencimentoPlano.mes = geraMes + 2;	
}




void acrescimoNoPlano(int porcentagem, int pos){
	int valorDaPorcentagem =  (fichas[pos].valorPlano * porcentagem)/100;
	fichas[pos].valorPlano = fichas[pos].valorPlano + valorDaPorcentagem;
}

void descontoNoPlano(int porcentagem, int pos){
	int valorDaPorcentagem =  (fichas[pos].valorPlano * porcentagem)/100;
	fichas[pos].valorPlano = fichas[pos].valorPlano - valorDaPorcentagem;
}

void recebeoEmail(int pos){
	
	printf("INFORME SEU EMAIL:\n");
	fflush(stdin);
	gets(fichas[pos].email);
	
	
}

void recebeoCPF(){

	int achou;
	
	do{
	
		printf("CPF:\n");
		fflush(stdin);
		char CPFinput[11];
	
		
		gets(CPFinput);
		int achou = verifica_se_CPF_existe(CPFinput);
		if(!achou){
			 sprintf(fichas[numeroCadastrados].cpf, CPFinput);	
			break;
		}
		
		if(achou){
			printf("CPF JA EXISTE!\n");
		}
	
	
	} while(1);

}


int verifica_se_CPF_existe(char CPF[11]){
	int achou = 0;
	


	for(int i = 0; i <= numeroCadastrados ; i++){
		
		if(strcmp(CPF, fichas[i].cpf) == 0){
			achou = 1;
			break;
		}
	}

	return achou;

}

void recebeTelefone(int pos){
	printf("INFORME SEU TELEFONE:\n");
	fflush(stdin);
	gets(fichas[pos].telefone);

	
}

void cliente_menor_que_13(int pos){
	if(fichas[pos].idade < 13){
		descontoNoPlano(30, pos);
	}
}


void cliente_maior_60anos(int pos){
	if(fichas[pos].idade >= 60){
		acrescimoNoPlano(40, pos);
	}
}

void recebeoNome(){
	printf("NOME:\n");
	fflush(stdin);
	gets(fichas[numeroCadastrados].nome);
	
	printf("SOBRENOME:\n");
	fflush(stdin);
	gets(fichas[numeroCadastrados].sobrenome);
		
}

void recebeoPlano(int pos){
	int opcaoPlano;
	imprimeMenuSeguro();
	printf("ESCOLHA UM PLANO DE SAUDE:\n");

	do{
	
	
	printf("OPCAO:\n");
	scanf("%d", &opcaoPlano);
	
	} while(opcaoPlano < 1 || opcaoPlano > 4);


	adicionaValorPlano(opcaoPlano, pos);

}

//UPDATE
void pesquisaClientePorCPF_PARAEDITAR(char CPF[11]){
	int posicao;
	int achei = -1;
	for(int i = 0; i < tamanho_ficha; i++){
		if(strcmp(fichas[i].cpf, CPF) == 0 ){
			achei = 1;
			posicao = i;
			break;
		}		
	}

	if(achei == -1){
		printf("CPF NAO ENCONTRADO!\n");
		editarCliente();
	}
	
	if(achei == 1){
		escolha_do_que_editar(posicao);
	}
}




void escolha_do_que_editar(int pos){
	
	
	//1
	printf("[1] - CPF:%s\n", fichas[pos].cpf);
	//2
	printf("[2] - NOME:%s %s\n", fichas[pos].nome, fichas[pos].sobrenome);
	//3
	printf("[3] - DATA NASCIMENTO: %d/%d/%d\n",fichas[pos].dataNascimento.dia, fichas[pos].dataNascimento.mes, fichas[pos].dataNascimento.ano );

	//4
	printf("[4] - SEXO:%d\n", fichas[pos].sexo);
	//5
	printf("[5] - EMAIL:%s\n", fichas[pos].email);
	//6
	printf("[6] - TELEFONE:%s\n", fichas[pos].telefone);
	//7
	printf("[7] - VALOR PLANO:%.2f\n", fichas[pos].valorPlano);

	printf("[0] - SAIR: \n");
	printf("IDADE: %d\n", fichas[pos].idade);
	int escolha;
	

	printf("O QUE DESEJA EDITAR?\n");
	
	do{
		
		printf("OPCAO:\n");
		scanf("%d", &escolha);
	 

	} while(escolha < 0 || escolha > 7);


	switch_edicao(escolha, pos);
	


}

void switch_edicao(int escolha, int pos){

	int opcao;
	switch(escolha){
		case 1:
			alteraCPF(pos);
			break;
		case 2:
			alteraNOME(pos);
			break;
		case 3:
			alteraDATA(pos);
			break;
		case 4:
			alteraSEXO(pos);
			break;
		case 5:
			alteraEMAIL(pos);
			break;
		case 6:
			alteraTELEFONE(pos);
			break;
		case 7:
			alteraPLANO(pos);
			break;

		case 0:
			break;
	}
}


void alteraCPF(int pos){
	
	// verificar se existe!!

	char CPFinput[11];
	printf("//ALTERAR CPF//\n");

	do{

		printf("NOVO CPF:");
		fflush(stdin);
		gets(CPFinput);
		int achou = verifica_se_CPF_existe(CPFinput);

		if(!achou){
	    	 sprintf(fichas[pos].cpf, CPFinput);	
		   	break;
			}
	
		if(achou){
			printf("CPF JA EXISTE!\n");
		} 


	} while(1);

	printf("CPF ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);
}

void alteraNOME(int pos){

	printf("\nNOME:\n");
	fflush(stdin);
	gets(fichas[pos].nome);
	
	printf("\nSOBRENOME:\n");
	fflush(stdin);
	gets(fichas[pos].sobrenome);
	printf("NOME ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);		
}


void alteraDATA(int pos){

	printf("DATA ANTES: %d/%d/%d %s\n", fichas[pos].dataNascimento.dia, fichas[pos].dataNascimento.mes, fichas[pos].dataNascimento.ano);
	printf("//ALTERAR DATA//\n");
	fflush(stdin);
	printf("DIA:\n");
	scanf("%d",&fichas[pos].dataNascimento.dia);
	printf("MES:\n");
	scanf("%d",&fichas[pos].dataNascimento.mes);
	printf("ANO:\n");
	scanf("%d",&fichas[pos].dataNascimento.ano);
	cadastraIdade(pos);
	
	printf("DATA ALTERADA COM SUCESSO!\n");
	escolha_do_que_editar(pos);
}

void alteraSEXO(int pos){

	
	printf("//ALTERAR SEXO//\n");
	recebeoSexo(pos);
	
	printf("SEXO ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);
}


void alteraEMAIL(int pos){
	
	printf("//ALTERAR EMAIL//\n");
	recebeoEmail(pos);

	printf("EMAIL ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);
}


void alteraTELEFONE(int pos){
	
	
	printf("//ALTERAR TELEFONE//\n");
	recebeTelefone(pos);
	printf("TELEFONE ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);
	
}


void alteraPLANO(int pos){

	printf("//ALTERA PLANO//\n");
	recebeoPlano(pos);
	if(fichas[pos].qtdDependente >= 1) descontoNoPlano(20, pos);
	printf("PLANO ALTERADO COM SUCESSO!\n");
	escolha_do_que_editar(pos);


}

//REMOVER 

void pesquisaClientePorCPF_PARAREMOVER(char CPF[11]){
	
	int posicao;
	int achei = -1;
	


	for(int i = 0; i < tamanho_ficha; i++){
		
		if(strcmp(fichas[i].cpf, CPF) == 0 ){
			achei = 1;
			posicao = i;
			break;
		}		
	}

	

	if(achei == -1){
		printf("CPF NAO ENCONTRADO!\n");
		
		removerCliente();
	}
	
	if(achei == 1){
		remocao(posicao);
	}
}


void remocao(int pos){
	
	sprintf(fichas[pos].cpf, "*");
	fichas[pos].ativo = 0;
	printf("CLIENTE REMOVIDO COM SUCESSO!\n");
}

int escolher_mes_a_ser_listado(){

	int escolha;

	printf("QUAL MES DESEJA LISTAR?\n");
	printf("[1] - JANEIRO // [2] - FEVEREIRO // [3] - MARCO // [4] - ABRIL // [5] - MAIO // [6] - JUNHO // \n[7] - JULHO // [8] - AGOSTO // [9] - SETEMBRO // [10] - OUTUBRO // [11] - NOVEMBRO // [12] - DEZEMBRO\n");
	
	do{
		
		printf("OPCAO:\n");
		scanf("%d", &escolha);
	
	} while(escolha < 1 || escolha > 12);

	return escolha;

}

void cabecalho_de_listagem(){
	
	
	printf("******************************************************************************************************************************************************************************************************************************\n");
	printf("    CPF                NOME                SEXO                FONE                EMAIL                IDADE                PLANO                DEPENDENTE                VALOR PLANO                VENCIMENTO DO PLANO\n");
	printf("******************************************************************************************************************************************************************************************************************************\n");
}




// METODOS DO MENU PRINCIPAL

void adicionarRegistro(){
	
	printf("//CADASTRO DE CLIENTE//\n");

	//RECEBENDO O NOME E SOBRENOME
	recebeoNome();

	//RECEBENDO O CPF
	recebeoCPF();
	

	//RECEBENDO O SEXO
	recebeoSexo(numeroCadastrados);
	
	//RECEBER EMAIL
	recebeoEmail(numeroCadastrados);
	

	//RECEBENDO DATA NASCIMENTO E CADASTRANDO IDADE
	recebeDataNascimento();

	//RECEBE O TELEFONE
	recebeTelefone(numeroCadastrados);

	//IMPRIMINDO MENU DE SEGUROS E ADICIONANDO O VALOR RELACIONADO AO PLANO
 	recebeoPlano(numeroCadastrados);
 		
	//CADASTRAR DEPENDENTE 
	verifica_e_cadastra_DEPENDENTE();
	
	//VERIFICAÇÕES
	cliente_menor_que_13(numeroCadastrados);
	cliente_maior_60anos(numeroCadastrados);

	//GERAR DATA VENCIMENTO
	gera_data_vencimento();
	

 	// 0 - INATIVO // 1 - ATIVO
	fichas[numeroCadastrados].ativo = 1;
	printf("CADASTRADO COM SUCESSO!\n");
	numeroCadastrados++;
}



void editarCliente(){


	printf("DIGITE O CPF DO CLIENTE QUE DESEJA EDITAR: [0] - VOLTAR PARA MENU PRINCIPAL\n");
	char cpfParaEditar[11];
	fflush(stdin);
	gets(cpfParaEditar);
	
	if(strcmp(cpfParaEditar, "0") != 0){
		pesquisaClientePorCPF_PARAEDITAR(cpfParaEditar);
	
	}



}

void removerCliente(){


	printf("DIGITE O CPF DO CLIENTE QUE DESEJA REMOVER: [0] - VOLTAR MENU PRINCIPAL\n");
	char cpfParaRemover[11];
	fflush(stdin);
	gets(cpfParaRemover);
	
	if(strcmp(cpfParaRemover, "0") != 0){
		pesquisaClientePorCPF_PARAREMOVER(cpfParaRemover);
			}
	
}

void listarGeral(){
	
	printf("\n//LISTAR GERAL//\n");	
	cabecalho_de_listagem();

	for(int i = 0; i <= numeroCadastrados; i++){
		if(strcmp(fichas[i].cpf, "*") != 0 && fichas[i].ativo == 1){
			printf("%s       ", fichas[i].cpf);
			printf("%s %s            ", fichas[i].nome, fichas[i].sobrenome);
			printf("%d              ", fichas[i].sexo);//AJEITAR PARA APARECER FEMININO
			printf("%s    ", fichas[i].telefone);
			printf("%s      ", fichas[i].email);
			printf("%d                ", fichas[i].idade);
			printf("%s                 ", fichas[i].nomeDoPlano);
			printf("%d                       ", fichas[i].qtdDependente);
			printf("  %.2f                      ", fichas[i].valorPlano);
			printf("     %d/%d\n", fichas[i].DatavencimentoPlano.dia, fichas[i].DatavencimentoPlano.mes);

		}	
	}
	printf("\n\n");



}

int escolhe_o_plano_para_listagem(){
	printf("QUAL PLANO?\n");
	printf("[1] - OURO // [2] - DIAMANTE // [3] - PRATA // [4] - ESMERALDA // [0] - MENU PRINCIPAL\n");
	int opcao;

	do{
		
		printf("OPCAO:\n");	
		scanf("%d", &opcao);
		

	} while(opcao < 0 || opcao > 4);


	switch(opcao){
		case 1:
			return 1;
		case 2: 
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		case 0:
			return 0;
	}
}




void listarPorPlano(){
	
	int escolha = escolhe_o_plano_para_listagem();

	
	
	if(escolha != 0){
		printf("//LISTAR POR PLANO//\n");
		cabecalho_de_listagem();
		for(int i = 0; i < numeroCadastrados; i++){
			if(fichas[i].tipoDoPlano == escolha && fichas[i].ativo == 1){
				printf("%s       ", fichas[i].cpf);
				printf("%s %s            ", fichas[i].nome, fichas[i].sobrenome);
				printf("%d              ", fichas[i].sexo);//AJEITAR PARA APARECER FEMININO
				printf("%s    ", fichas[i].telefone);
				printf("%s      ", fichas[i].email);
				printf("%d                ", fichas[i].idade);
				printf("%s                 ", fichas[i].nomeDoPlano);
				printf("%d                       ", fichas[i].qtdDependente);
				printf("  %.2f                      ", fichas[i].valorPlano);
				printf("     %d/%d\n", fichas[i].DatavencimentoPlano.dia, fichas[i].DatavencimentoPlano.mes);
	
			}	
		}
	}

	if(escolha == 0){
		menu_principal();
	}

	printf("\n");


}

void listarPorVencimentoNoMes(){


	time_t mytime;
	mytime = time(NULL);
    struct tm tm = *localtime(&mytime);
    int mes_atual = tm.tm_mon + 1;


    int mes_escolhido = escolher_mes_a_ser_listado();

    printf("\n//LISTAR POR VENCIMENTO//\n");
    cabecalho_de_listagem();



    for(int i = 0; i < numeroCadastrados; i++){
			if( fichas[i].DatavencimentoPlano.mes == mes_escolhido && fichas[i].ativo == 1){
				printf("%s       ", fichas[i].cpf);
				printf("%s %s            ", fichas[i].nome, fichas[i].sobrenome);
				printf("%d              ", fichas[i].sexo);//AJEITAR PARA APARECER FEMININO
				printf("%s    ", fichas[i].telefone);
				printf("%s      ", fichas[i].email);
				printf("%d                ", fichas[i].idade);
				printf("%s                 ", fichas[i].nomeDoPlano);
				printf("%d                       ", fichas[i].qtdDependente);
				printf("  %.2f                      ", fichas[i].valorPlano);
				printf("     %d/%d\n", fichas[i].DatavencimentoPlano.dia, fichas[i].DatavencimentoPlano.mes);
	
			}	
		}
	
		printf("\n");
	}

	



//SWITCH DE OPCOES NO MENU

void switchOpcoes(int opcoes){
	switch(opcoes){
		case 1:
		adicionarRegistro();
		break;
		
		case 2:
		editarCliente();
		break;

		case 3:
		removerCliente();
		break;

		case 4:
		listarGeral();
		break;

		case 5:
		listarPorPlano();
		break;

		case 6:
		listarPorVencimentoNoMes();
		break;

		case 0:
		exit(EXIT_SUCCESS);


	}
}

int escolhe_opcao_do_menu(){
	int escolha_do_menu;

	do{

		printf("ESCOLHA UMA OPCAO:\n");
		
		scanf("%d", &escolha_do_menu);


	} while(escolha_do_menu < 0 || escolha_do_menu > 6);


	return escolha_do_menu;
}


void menu_principal(){
	int pediuParaSair = 0;
	int opcao;
	
	do{
	imprimeMenu();
	
	opcao = escolhe_opcao_do_menu();

	if(opcao == 0) {
		pediuParaSair = 1;
		break;
	}

	switchOpcoes(opcao);
	


	} while(!pediuParaSair);


}



int main(){
	

	menu_principal();

}



	

