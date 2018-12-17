package br.com.acme.provider.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.provider.model.Provider;
import br.com.acme.provider.repository.ProviderRepository;

@RestController
public class ProviderResource {

	@Autowired
	private ProviderRepository providerRepository;

	@RequestMapping(value = "/provider/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<Provider>> findAll() {
		List<Provider> listaProvider = this.providerRepository.findAll();
		if (listaProvider == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Provider>>(listaProvider, HttpStatus.OK);
	}

	@RequestMapping(value = "/provider/findById", method = RequestMethod.POST)
	public ResponseEntity<String> findById(@RequestParam("id") Integer id) {
		try {
			Optional<Provider> optionalProvider = this.providerRepository.findById(id);
			if (optionalProvider != null) {
				return new ResponseEntity<String>(
						"Query by id [" + id +"] - provider! Name: " + optionalProvider.get().getName(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Provider not found!", HttpStatus.FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/provider/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertProvider(@RequestParam("name") String name,
			@RequestParam("address") String address) {
		try {
			Provider provider = new Provider(name, address);
			this.providerRepository.save(provider);
			return new ResponseEntity<String>("Insert provider success! " + provider.getName(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/provider/update", method = RequestMethod.POST)
	public ResponseEntity<String> insertProvider(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("address") String address) {
		try {
			Optional<Provider> optionalProvider = this.providerRepository.findById(id);
			if (optionalProvider != null) {
				Provider provider = optionalProvider.get();
				provider.setName(name);
				provider.setAddress(address);
				this.providerRepository.save(provider);
				return new ResponseEntity<String>("Update provider success! Name: " + provider.getName(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Provider not found!", HttpStatus.FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/provider/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteProvider(@RequestParam("id") Integer id) {
		try {
			Optional<Provider> optionalProvider = this.providerRepository.findById(id);
			if (optionalProvider != null) {
				this.providerRepository.delete(optionalProvider.get());
				return new ResponseEntity<String>("Deleted provider success! " + optionalProvider.get().getName(),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Provider not found!", HttpStatus.FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
