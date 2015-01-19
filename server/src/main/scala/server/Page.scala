package server

import scalatags.Text.all._

object Page {
  
  def htmlContent(client: String = "Client", elementId: String = "content") = html(
    head(
      meta(charset := "UTF-8"),
      script(src:= "/client-fastopt.js"),
      script(src:= "/client-jsdeps.js")
    ),
    body(
      style:= "canvas: { height: 100%; width: 100% }",
      onload:= s"$client().main(document.getElementById('$elementId'))",
      div(id:=elementId)
    )
  ).render
}