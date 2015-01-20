package server

import scalatags.Text.all._

object Page {
  
  def htmlContent(client: String = "Client") = html(
    head(
      meta(charset := "UTF-8"),
      script(src:= "/client-fastopt.js"),
      script(src:= "/client-jsdeps.js")
    ),
    body(
      style:= "margin: 0; overflow: hidden;",
      onload:= client + "().main()"
    )
  ).render
}