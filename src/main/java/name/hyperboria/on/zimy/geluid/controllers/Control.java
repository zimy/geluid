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
    public @ResponseBody String index()
    {
        return "index page!";
    }

    @RequestMapping("/admin")
    public @ResponseBody String admin() {return "admin, lol";}

    @RequestMapping("/login")
    public String login() {return "login";}
}
