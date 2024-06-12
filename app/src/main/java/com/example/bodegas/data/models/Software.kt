package com.example.bodegas.data.models

data class Software(
    val idEquipo: Int,
    val EdicionSistemaOperativo: String,
    val VersionSistemaOperativo: String,
    val edicionOffice: String,
    val versionOffice: String,
    val edicionAntivirus: String,
    val versionAntivirus: String,
    val maquinaVirtual: String,
    val versionMaquinaVirtual: String,
    val otrosProgramas: String,
    val versionOtrosProgramas: String,
    val sistemaSoporteRemoto: Boolean,
    val nombreSoporteRemoto: String
)

val sistemasOperativos = listOf(
    "Windows XP",
    "Windows 7",
    "Windows 8",
    "Windows 8.1",
    "Windows 10",
    "Windows 11",
    "Linux",
    "Chrome OS",
    "MacOS",
    "No tiene"
)

val versionesSistemasOperativos = listOf(
    "Service Pack 1",
    "Service Pack 2",
    "Service Pack 3",
    "Version 1507",
    "Version 1511",
    "Version 1607",
    "Version 1703",
    "Version 1709",
    "Version 1803",
    "Version 1809",
    "Version 1903",
    "Version 1909",
    "Version 2004",
    "Version 20H2",
    "Version 21H1",
    "Version 21H2",
    "Version 22H1",
    "Version 22H2",
    "Version 23H1",
    "OS X 10: Kodiak",
    "OS X 10.0: Cheetah",
    "OS X 10.1: Puma",
    "OS X 10.2: Jaguar",
    "OS X 10.3: Panther",
    "OS X 10.4: Tiger",
    "OS X 10.4.4: Tiger",
    "OS X 10.5: Leopard",
    "OS X 10.6: Snow Leopard",
    "OS X 10.7: Lion",
    "OS X 10.8: Mountain Lion",
    "OS X 10.9: Mavericks",
    "OS X 10.10: Yosemite",
    "OS X 10.11: El Capitan",
    "macOS 10.12: Sierra",
    "macOS 10.13: High Sierra",
    "macOS 10.14: Mojave",
    "macOS 10.15: Catalina",
    "macOS 11.0: Big Sur",
    "macOS 12.0: Monterey",
    "macOS 13.0: Ventura",
    "macOS 14.0: Sonoma",
    "No tiene"
)

val edicionOffice = listOf(
    "Office 95",
    "Office 97",
    "Office 2000",
    "Office XP",
    "Office 2003",
    "Office 2007",
    "Office 2010",
    "Office 2013",
    "Office 2016",
    "Office 2019",
    "Office 365",
    "Office 2021",
    "WPS",
    "LibreOffice",
    "No tiene"
)

val versionOffice = listOf(
    "Home & Student",
    "Home & Business",
    "Standard",
    "Professional",
    "Professional Plus",
    "LTSC Standard",
    "LTSC Professional Plus",
    "Familia",
    "Personal",
    "Hogar y Estudiantes",
    "Microsoft 365 para negocios",
    "Empresa estándar",
    "Office 365 E1",
    "Office 365 E2",
    "Office 365 E3",
    "No tiene"
)

