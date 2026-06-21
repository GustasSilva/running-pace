package aplicativo;

public class salvar_dados {
    private String nome_piloto;	
    private String nome_equipe;
    private String numero_carro;

    public salvar_dados( 
        String nome_piloto,
        String nome_equipe, 
        String numero_carro)
        {
            this.nome_piloto = nome_piloto;
            this.nome_equipe = nome_equipe;
            this.numero_carro = numero_carro;
        }
        public String getNome_piloto(){
			return this.nome_piloto;}
		public String getNome_equipe(){
			return this.nome_equipe;}
		public String getNumero_carro(){
			return this.numero_carro;}
}
        
 
