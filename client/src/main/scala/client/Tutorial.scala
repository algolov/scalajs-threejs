package client

import scalajs.js
import js.annotation.JSExport
import org.scalajs.threejs._

import org.scalajs.dom
import dom. { document, window }
//import scalatags.JsDom.all._


@JSExport
object Tutorial {
  @JSExport
  def main() {
    
    val scene = new Scene
    
    val camera = new PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 0.1, 1000)
    
    val renderer = new WebGLRenderer
    
    renderer.setClearColor(new Color().setHex(0xEEEEEE), 1)
    renderer.setSize(window.innerWidth, window.innerHeight)
    renderer.shadowMapEnabled = true
    
    val axes = new AxisHelper(20)
    
    scene.add(axes)
    
    val planeGeometry = new PlaneGeometry(60, 20, 1, 1)
    val planeMaterial = new MeshLambertMaterial(js.Dynamic.literal(
      color = new Color().setHex(0xcccccc)
    ).asInstanceOf[MeshLambertMaterialParameters])
    
    val plane = new Mesh(planeGeometry, planeMaterial)
    
    plane.rotation.x = -0.5 * Math.PI
    plane.position.set(15, 0, 0)
    plane.receiveShadow = true
    
    scene.add(plane)
    
    val boxGeometry = new BoxGeometry(4, 4, 4)
    
    val material = new MeshLambertMaterial(js.Dynamic.literal(
      color = new Color().setHex(0xff0000)
    ).asInstanceOf[MeshLambertMaterialParameters])
    
    val cube = new Mesh( boxGeometry, material )
    
    cube.position.set(-4, 3, 0)
    cube.castShadow = true
           
    scene.add(cube)
    
    
    val sphereGeometry = new SphereGeometry(4, 20, 20)
    val sphereMaterial = new MeshLambertMaterial(js.Dynamic.literal(
      color = new Color().setHex(0x7777ff)
    ).asInstanceOf[MeshLambertMaterialParameters])
    
    val sphere = new Mesh(sphereGeometry, sphereMaterial)    
    sphere.position.set(20, 4, 2)
    sphere.castShadow = true
    
    scene.add( sphere )
    
    camera.position.set(-30, 40, 30)
    camera.lookAt(scene.position)
    
    val spotLight = new SpotLight(0xffffff)
    spotLight.position.set(-40, 60, -10)
    spotLight.castShadow = true
    
    scene.add(spotLight)
    
    document.body.appendChild( renderer.domElement )     
      
    renderer.render( scene, camera )
  }
}