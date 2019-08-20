<?php

//$content = trim(file_get_contents("php://input"));
//$content2 = json_decode($content);
//var_dump($content2);

$data = json_decode(file_get_contents('php://input'), true);
//print_r($data);
//echo $data["cedula"];
//$result_2 = $_POST['datos'];

//$decoded = json_decode($resultado, true);


if (isset($_GET["datos"])) {
    //$data=array();
    //$data["datos"]=$_GET["datos"];
    //$data["coordenadas"]=$_GET["coordenadas"];
    //$data["cedula"]=$_GET["cedula"];
    echo ($_GET["datos"]);
    //echo json_encode(($_GET["datos"]." - ".$_GET["coordenadas"]." - ".$_GET["cedula"]));
}else if (isset ($data["datos"])) {
    //$data=array();
    //$data["datos"]=$decoded->datos;
    //$data["coordenadas"]=$decoded->coordenadas;
    //$data["cedula"]=$decoded->cedula;
    //echo ($content2["datos"]);

    $file = fopen("archivo.txt", "a");
  //  fwrite($file, "Datos: ".$decoded->datos. PHP_EOL);
    //fwrite($file, "Coordenadas: ".$decoded->coordenadas. PHP_EOL);
    fwrite($file, "datos: ".$data["datos"]. PHP_EOL);
    fwrite($file, "coordenadas: ".$data["coordenadas"]. PHP_EOL);
    fwrite($file, "cedula: ".$data["cedula"]. PHP_EOL);
    fclose($file);
    //echo json_encode($_POST["datos"].$_POST["coordenadas"].$_POST["cedula"]);

}

?>