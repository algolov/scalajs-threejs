package client

import scalajs.js
import js.annotation.JSExport
import org.scalajs.threejs._
//import org.scalajs.threejs.THREE._

import org.scalajs.dom
import dom. { document, window }
//import scalatags.JsDom.all._


@JSExport
object Threejs {
  @JSExport
  def main(container: dom.HTMLDivElement) {
    
    document.body.style.margin = "0"
    
    val scene = new Scene
    
    val camera = new PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000)
    
    val renderer = new WebGLRenderer
    
    renderer.setSize(window.innerWidth, window.innerHeight)
    
    document.body.appendChild( renderer.domElement )
    
    val geometry = new BoxGeometry(1, 1, 1)
    
    val material = new MeshBasicMaterial(js.Dynamic.literal(
      color = new Color().setHex(0x00ff00)
    ).asInstanceOf[MeshBasicMaterialParameters])
    
    val cube = new Mesh( geometry, material )
    
    scene.add(cube)
    
    camera.position.z = 5
    
    def render(param: Double): Unit = {
      
      dom.requestAnimationFrame(  render _)
      
      cube.rotateX(0.1)
      cube.rotateY(0.1)
      
      renderer.render( scene, camera )
    }
    
    render(2)
    
  }
}