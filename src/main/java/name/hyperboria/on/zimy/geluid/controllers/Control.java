package name.hyperboria.on.zimy.geluid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO template javadoc
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 01.08.14
 */
@Controller
public class Control {
    @RequestMapping({"/welcome","/"})
    public String index()
    {
        return "home";
    }

    @RequestMapping("/admin")
    public String admin() {return "admin";}

    @RequestMapping("/login")
    public String login() {return "login";}
}
