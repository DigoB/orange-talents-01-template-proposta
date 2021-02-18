package br.com.zup.projetopropostacartao.feign;

import br.com.zup.projetopropostacartao.cartao.forms.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartaoClient", url = "${feign.cartao.url}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/cartoes")
    CartaoResponse solicitaCartao(@RequestParam("idProposta") Long id);
}