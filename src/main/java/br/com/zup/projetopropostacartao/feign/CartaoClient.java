package br.com.zup.projetopropostacartao.feign;

import br.com.zup.projetopropostacartao.avisos.AvisoRequest;
import br.com.zup.projetopropostacartao.avisos.AvisoResponse;
import br.com.zup.projetopropostacartao.bloqueios.BloqueioRequest;
import br.com.zup.projetopropostacartao.bloqueios.BloqueioResponse;
import br.com.zup.projetopropostacartao.cartao.forms.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartaoClient", url = "${feign.cartao.url}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/cartoes")
    CartaoResponse solicitaCartao(@RequestParam("idProposta") Long id);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueio(@PathVariable String id, @RequestBody BloqueioRequest request);

	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes/{id}/avisos")
    AvisoResponse solicitaViagem(@PathVariable String id, @RequestBody AvisoRequest avisoRequest);
}