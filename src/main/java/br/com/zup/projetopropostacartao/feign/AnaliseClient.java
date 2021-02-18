package br.com.zup.projetopropostacartao.feign;

import br.com.zup.projetopropostacartao.propostas.Proposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseclient", url = "${feign.analise.url}")
public interface AnaliseClient {

    @PostMapping("/api/solicitacao")
    ConsultaStatusResponse consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

    class ConsultaStatusRequest {

        private String documento;
        private String nome;
        private Long idProposta;

        public ConsultaStatusRequest(Proposta novaProposta) {
            this.documento = novaProposta.getDocumento();
            this.nome = novaProposta.getNome();
            this.idProposta = novaProposta.getId();
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public Long getIdProposta() {
            return idProposta;
        }
    }

    class ConsultaStatusResponse {

        private String documento;
        private String nome;
        private Long idProposta;
        private String resultadoDaSolicitacao;

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public Long getIdProposta() {
            return idProposta;
        }

        public String getResultadoDaSolicitacao() {
            return resultadoDaSolicitacao;
        }
    }
}
