package ee.ttu

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ErrorController {

    def index() {
        if (request.xhr) {
            response.status = 200
            render(template: "/error/ajaxErrorTemplate")
        } else {
            render view: "/error"
        }
    }
}
