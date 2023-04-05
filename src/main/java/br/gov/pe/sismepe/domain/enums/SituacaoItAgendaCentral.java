package br.gov.pe.sismepe.domain.enums;

public enum SituacaoItAgendaCentral {
	
		LIVRE("L", "Livre"),
		AGENDADO("A", "Agendado"),
		CANCELADO("C", "Cancelado"),
		BLOQUEADO("B", "Bloqueado"),
		REALIZADO("R", "Realizado"),
		TRANSFERIDO("T", "Transferido"),
		PARA_TRANSFERIR("P", "Para transferir"),
		FALTA_PRESTADO("M", "Falta do prestador");
		
		private String codigo;
		private String descricao;

		private SituacaoItAgendaCentral(String codigo, String descricao) {
			this.codigo = codigo;	
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public static SituacaoItAgendaCentral toEnum(String codigo) {
			SituacaoItAgendaCentral retorno = null;
			if (codigo != null) {
				for (SituacaoItAgendaCentral tipo : SituacaoItAgendaCentral.values()) {
					if (tipo.getCodigo().equals(codigo)) {
						retorno = tipo;
					}
				}
			}

			if (retorno == null) {
				throw new IllegalArgumentException("Código inválido: " + codigo);
			}
			return retorno;
		}

		public String getCodigo() {
			return codigo;
		}
	}