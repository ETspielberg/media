<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mabxml="http://www.ddb.de/professionell/mabxml/mabxml-1.xsd"
                exclude-result-prefixes="xsl mabxml"
>

    <xsl:template match="mabxml:datensatz">
        <bibliographicInformation>
            <authors>
                <xsl:for-each select="mabxml:feld[@nr &gt;='200' and @nr &lt;='296']|
              			mabxml:feld[@nr &gt;='100' and @nr &lt;='196']">
                    <author>
                        <xsl:apply-templates select="mabxml:uf[@code='p']"/>
                    </author>
                </xsl:for-each>
            </authors>
            <isbn>
                <xsl:apply-templates select="mabxml:feld[@nr='540']"/>
            </isbn>
            <doi>
                <xsl:apply-templates select="mabxml:feld[@nr='552']"/>
            </doi>
            <publisher>
                <xsl:apply-templates select="mabxml:feld[@nr='412']"/>
            </publisher>
            <year>
                <xsl:apply-templates select="mabxml:feld[@nr='425']"/>
            </year>
            <edition>
                <xsl:apply-templates select="mabxml:feld[@nr='403']"/>
            </edition>
            <series>
            </series>
            <volume></volume>
            <keywords>
                <xsl:call-template name="keywords"/>
            </keywords>
            <!--<title>
                <xsl:apply-templates select="mabxml:feld[@nr &gt;='310']"/>
                <xsl:apply-templates select="mabxml:feld[@nr &gt;='331']"/>
            </title>
            <subtitle>
                <xsl:apply-templates select="mabxml:feld[@nr &gt;='335']"/>
                <xsl:apply-templates select="mabxml:feld[@nr &gt;='340']"/>
                <xsl:apply-templates select="mabxml:feld[@nr &gt;='341']"/>
            </subtitle>-->
        </bibliographicInformation>
    </xsl:template>

    <!-- Sammlungsvermerk -->
    <xsl:template match="mabxml:feld[@nr='300']">
        <xsl:text>[</xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
        <xsl:text>]</xsl:text>
    </xsl:template>

    <!-- Personennameneintraege -->
    <xsl:template match="mabxml:feld[@nr &gt;='100' and @nr &lt;='196']">
            <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            <xsl:apply-templates select="mabxml:uf[@code='p']"/>
    </xsl:template>

    <!-- Einheitssachtitel -->
    <xsl:template match="mabxml:feld[@nr='304']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Ansetzungssachtitel -->
    <xsl:template match="mabxml:feld[@nr='310']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Hauptsachtitel -->
    <xsl:template match="mabxml:feld[@nr='331']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Zu ergaenzender Urheber, bzw. Verfasserangabe -->
    <xsl:template match="mabxml:feld[@nr='333' or @nr='342' or @nr='346' or @nr='359']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Allgemeine Materialbenennung -->
    <xsl:template match="mabxml:feld[@nr='334']">
        <xsl:text> [</xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
        <xsl:text>]</xsl:text>
    </xsl:template>

    <!-- Zusatz zum Sachtitel -->
    <xsl:template match="mabxml:feld[@nr='335' or @nr='343' or @nr='347' or @nr='412' or @nr='417' or @nr='434']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Parallelsachtitel in Ansetzungsform -->
    <xsl:template match="mabxml:feld[@nr='340' or @nr='344']">
        <xsl:text> = [</xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
        <xsl:text>]</xsl:text>
    </xsl:template>

    <!-- Parallelsachtitel in Vorlageform -->
    <xsl:template match="mabxml:feld[@nr='341' or @nr='345']">
        <xsl:if test="boolean(../mabxml:feld[@nr=current()/@nr - 1])=false">
            <xsl:text> =</xsl:text>
        </xsl:if>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Unterreihe, beigefuegte Werke, Zusaetze zur gesamten Vorlage, Verfasserangabe zur gesamten Vorlage (werden mit ". " angeschlossen) -->
    <xsl:template match="mabxml:feld[@nr &gt;='360' and @nr &lt;='369']">
        <xsl:call-template name="endpunkt"/>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Ausgabebezeichnung -->
    <xsl:template match="mabxml:feld[@nr='403']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Erscheinungsverlauf -->
    <xsl:template match="mabxml:feld[@nr='405']">
        <xsl:choose>
            <xsl:when test="../mabxml:feld[@nr='403']">
                <xsl:call-template name="endpunkt"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="anzahlknoten"
                              select="count(../mabxml:feld[@nr='370']) + count(../mabxml:feld[@nr='370']/mabxml:uf) + count(../mabxml:feld[@nr='370']/mabxml:uf/ns)"/>
                <xsl:call-template name="endpunkt">
                    <xsl:with-param name="abstand" select="$anzahlknoten"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text> - </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- mathematische Angaben -->
    <xsl:template match="mabxml:feld[@nr='407']">
        <xsl:choose>
            <xsl:when test="../mabxml:feld[@nr='403'] or ../mabxml:feld[@nr='405']">
                <xsl:call-template name="endpunkt"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="anzahlknoten"
                              select="count(../mabxml:feld[@nr='370']) + count(../mabxml:feld[@nr='370']/mabxml:uf) + count(../mabxml:feld[@nr='370']/mabxml:uf/ns)"/>
                <xsl:call-template name="endpunkt">
                    <xsl:with-param name="abstand" select="$anzahlknoten"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text> - </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Verlagsort -->
    <xsl:template match="mabxml:feld[@nr='410']">
        <br/>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Ort des 2. Verlags bzw. Beigabenvermerk (werden mit " ; " angeschlossen) -->
    <xsl:template match="mabxml:feld[@nr='415' or @nr='435']">
        <xsl:text> ; </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Erscheinungsjahr -->
    <xsl:template match="mabxml:feld[@nr='425']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!--
      Setzt das Deskriptionszeichen vor der Umfangsangabe mit Pr�fung
      von nicht auszugebenden Inhalten in mabxml:feld 425 zur Vermeidung
      doppelter Punkte. (f�r 433 und 437)
    -->

    <xsl:template name="deskriptzeichenvorumfang">
        <xsl:choose>
            <xsl:when
                    test="preceding-sibling::*[1]/@nr='425' and (preceding-sibling::*[1]/@ind='b' or preceding-sibling::*[1]/@ind='c')">
                <xsl:variable name="anzahlknoten"
                              select="count(../mabxml:feld[@nr='425']) + count(../mabxml:feld[@nr='425']/mabxml:uf)"/>
                <xsl:call-template name="endpunkt">
                    <xsl:with-param name="abstand" select="$anzahlknoten"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="endpunkt"/>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text> - </xsl:text>
    </xsl:template>

    <!-- Umfangsangabe -->
    <xsl:template match="mabxml:feld[@nr='433']">
        <xsl:call-template name="deskriptzeichenvorumfang"/>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Begleitmaterial -->
    <xsl:template match="mabxml:feld[@nr='437']">
        <xsl:choose>
            <xsl:when test="../mabxml:feld[@nr='433']">
                <xsl:text> + </xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="deskriptzeichenvorumfang"/>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Gesamttitel -->
    <xsl:template match="mabxml:feld[@nr='451' or @nr='461' or @nr='471' or @nr='481' or @nr='491']">
        <xsl:choose>
            <xsl:when test="@nr='451'">
                <xsl:choose>
                    <xsl:when test="../@typ='h'">
                        <br/>
                        <xsl:text>(</xsl:text>
                    </xsl:when>
                    <xsl:when test="../@typ='u'">
                        <xsl:call-template name="endpunkt"/>
                        <xsl:text> - (</xsl:text>
                    </xsl:when>
                </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text> (</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:choose>
            <xsl:when test="../mabxml:feld[@nr=current()/@nr + 2]">
                <a href="{../mabxml:feld[@nr=current()/@nr + 2]/mabxml:uf[@code='a']/text()}.html">
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </a>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!-- Fussnoten -->
    <xsl:template match="mabxml:feld[@nr &gt;='501'and @nr &lt;='534']">
        <xsl:choose>
            <xsl:when test="../@typ='h' or position()=1">
                <br/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="endpunkt"/>
                <xsl:text> - </xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:if test="mabxml:uf[@code='p']">
            <xsl:apply-templates select="mabxml:uf[@code='p']"/>
            <xsl:text>: </xsl:text>
        </xsl:if>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Standardnummern -->
    <xsl:template match="mabxml:feld[@nr &gt;='540' and @nr &lt;='543']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Bestellnummern -->
    <xsl:template match="mabxml:feld[@nr='551']">
        <xsl:choose>
            <xsl:when test="position()=1">
                <br/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text> - </xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>Best.-Nr. </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Persistent identifier -->
    <xsl:template match="mabxml:feld[@nr='552']">
        <xsl:choose>
            <xsl:when test="@ind='a'">
                <doi>
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </doi>
            </xsl:when>
            <xsl:when test="@ind='b'">
                <urn>
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </urn>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <!-- Reportnummern -->
    <xsl:template match="mabxml:feld[@nr='556']">
        <br/>
        <xsl:choose>
            <xsl:when test="@ind='a'">
                <xsl:text>Reportnr. </xsl:text>
            </xsl:when>
            <xsl:when test="@ind='b'">
                <xsl:text>Kontraktnr. </xsl:text>
            </xsl:when>
            <xsl:when test="@ind='c'">
                <xsl:text>Task-Nr. </xsl:text>
            </xsl:when>
        </xsl:choose>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Sonstige Nummern -->
    <xsl:template match="mabxml:feld[@nr='580']">
        <xsl:choose>
            <xsl:when test="position()=1">
                <br/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text> - </xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Sekundaerformen -->
    <xsl:template match="mabxml:feld[@nr &gt;='610' and @nr &lt;='647']">
        <xsl:choose>
            <xsl:when test="@nr='610'">
                <br/>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                <xsl:text>: </xsl:text>
            </xsl:when>
            <xsl:when test="@nr='611'">
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>
            <xsl:when test="@nr='613'">
                <xsl:if test="preceding-sibling::*[1]/@nr='611'">
                    <xsl:text> : </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>
            <xsl:when test="@nr='614'">
                <xsl:text> ; </xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>
            <xsl:when test="@nr='616'">
                <xsl:if test="preceding-sibling::*[1]/@nr='614'">
                    <xsl:text> : </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>
            <xsl:when test="@nr='619'">
                <xsl:if test="preceding-sibling::*[1]/@nr!='610'">
                    <xsl:text>, </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>
            <xsl:when test="@nr='621'">
                <xsl:text>. (</xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                <xsl:text>)</xsl:text>
            </xsl:when>

            <xsl:when test="@nr='634'">
                <xsl:choose>
                    <xsl:when test="position()=1">
                        <xsl:text>. </xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text> - </xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:text>ISBN </xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>

            <xsl:when test="@nr='636'">
                <xsl:text>. </xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
            </xsl:when>

            <xsl:when test="@nr='637'">
                <xsl:text>. (</xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                <xsl:if test="../mabxml:feld[@nr='638']">
                    <xsl:text> + </xsl:text>
                    <xsl:value-of select="../mabxml:feld[@nr='638']/mabxml:uf"/>
                </xsl:if>
                <xsl:text>)</xsl:text>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <!-- Elektronische Ressource - Systemvoraussetzungen -->
    <xsl:template match="mabxml:feld[@nr='651']">
        <xsl:choose>
            <xsl:when test="position()=1">
                <xsl:choose>
                    <xsl:when test="../@typ='h'">
                        <br/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:variable name="anzahlknoten"
                                      select="count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '651']) +
                      count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '651']/mabxml:uf) +
                      count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '651']/mabxml:uf/ns)"/>
                        <xsl:call-template name="endpunkt">
                            <xsl:with-param name="abstand" select="$anzahlknoten"/>
                        </xsl:call-template>
                        <xsl:text> - </xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>

            <xsl:otherwise>
                <xsl:call-template name="endpunkt"/>
                <xsl:text> - </xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="mabxml:uf[@code='b']"/>
    </xsl:template>

    <!-- Elektronische Ressource - Online-Ressource -->
    <xsl:template match="mabxml:feld[@nr='652']">
        <xsl:if test="mabxml:uf[@code='a']='Online-Ressource'">
            <xsl:choose>
                <xsl:when test="../mabxml:feld[@nr='610']">
                    <xsl:if test="preceding-sibling::*[1]/@nr!='610'">
                        <xsl:text>. </xsl:text>
                    </xsl:if>
                    <xsl:text/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:variable name="anzahlknoten"
                                  select="count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '652']) +
                    count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '652']/mabxml:uf) +
                    count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '652']/mabxml:uf/ns)"/>

                    <xsl:choose>
                        <xsl:when test="../mabxml:feld[@nr='425']/@ind='b' or ../mabxml:feld[@nr='425']/@ind='c'">
                            <xsl:variable name="anzahl425"
                                          select="count(../mabxml:feld[@nr='425']) + count(../mabxml:feld[@nr='425']/mabxml:uf)"/>
                            <xsl:call-template name="endpunkt">
                                <xsl:with-param name="abstand" select="$anzahlknoten + $anzahl425"/>
                            </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:call-template name="endpunkt">
                                <xsl:with-param name="abstand" select="$anzahlknoten"/>
                            </xsl:call-template>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:text> - </xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates select="mabxml:uf[@code='a']"/>
        </xsl:if>
    </xsl:template>

    <!-- Elektronische Ressource - Physische Beschreibung -->
    <xsl:template match="mabxml:feld[@nr='653']">
        <xsl:if test="../mabxml:feld[@nr='652']/mabxml:uf[@code='a'] != 'Online-Ressource'">
            <xsl:variable name="anzahlknoten"
                          select="count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '653']) +
                count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '653']/mabxml:uf) +
                count(../mabxml:feld[@nr &gt; '425' and @nr &lt; '653']/mabxml:uf/ns)"/>
            <xsl:choose>
                <xsl:when test="../mabxml:feld[@nr='425']/@ind='b' or ../mabxml:feld[@nr='425']/@ind='c'">
                    <xsl:variable name="anzahl425"
                                  select="count(../mabxml:feld[@nr='425']) + count(../mabxml:feld[@nr='425']/mabxml:uf)"/>
                    <xsl:call-template name="endpunkt">
                        <xsl:with-param name="abstand" select="$anzahlknoten + $anzahl425"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:call-template name="endpunkt">
                        <xsl:with-param name="abstand" select="$anzahlknoten"/>
                    </xsl:call-template>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text> - </xsl:text>
            <xsl:apply-templates select="mabxml:uf[@code='a']"/>

            <xsl:if test="mabxml:uf[@code='b']">
                <xsl:text> (</xsl:text>
                <xsl:apply-templates select="mabxml:uf[@code='b']"/>
                <xsl:text>)</xsl:text>
            </xsl:if>

            <xsl:if test="mabxml:uf[@code='c']">
                <xsl:if test="mabxml:uf[@code='a'] or mabxml:uf[@code='b']">
                    <xsl:text> : </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='c']"/>
            </xsl:if>

            <xsl:if test="mabxml:uf[@code='d']">
                <xsl:if test="mabxml:uf[@code='a'] or mabxml:uf[@code='b'] or mabxml:uf[@code='c']">
                    <xsl:text> ; </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='d']"/>
            </xsl:if>

            <xsl:if test="mabxml:uf[@code='e']">
                <xsl:if test="mabxml:uf[@code='a'] or mabxml:uf[@code='b'] or mabxml:uf[@code='c'] or mabxml:uf[@code='d']">
                    <xsl:text> + </xsl:text>
                </xsl:if>
                <xsl:apply-templates select="mabxml:uf[@code='e']"/>
            </xsl:if>
        </xsl:if>
    </xsl:template>

    <!-- Elektronische Ressource - Zugang und Adresse im Fernzugriff -->
    <xsl:template match="mabxml:feld[@nr='655']">
        <xsl:choose>
            <xsl:when test="../mabxml:feld[@nr='651']">
                <xsl:call-template name="endpunkt"/>
                <xsl:text> - </xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:choose>
                    <xsl:when test="../@typ='u'">
                        <xsl:variable name="anzahlknoten"
                                      select="count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '655']) +
                      count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '655']/mabxml:uf) +
                      count(../mabxml:feld[@nr &gt; '499' and @nr &lt; '655']/mabxml:uf/ns)"/>
                        <xsl:call-template name="endpunkt">
                            <xsl:with-param name="abstand" select="$anzahlknoten"/>
                        </xsl:call-template>
                        <xsl:text> - </xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <br/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>Zugriffsart: </xsl:text>

        <xsl:choose>
            <xsl:when test="@ind='a'">
                <xsl:text>E-Mail</xsl:text>
            </xsl:when>
            <xsl:when test="@ind='b'">
                <xsl:text>FTP</xsl:text>
            </xsl:when>
            <xsl:when test="@ind='c'">
                <xsl:text>Remote Login</xsl:text>
            </xsl:when>
            <xsl:when test="@ind='d'">
                <xsl:text>Dial-Up</xsl:text>
            </xsl:when>
            <xsl:when test="@ind='e'">
                <xsl:text>HTTP</xsl:text>
            </xsl:when>
            <xsl:when test="@ind='h'">
                <xsl:value-of select="mabxml:uf[@code='2']"/>
            </xsl:when>
        </xsl:choose>
        <xsl:text>. - Adresse: </xsl:text>
        <a href="{mabxml:uf[@code='u']}">
            <xsl:apply-templates select="mabxml:uf[@code='u']"/>
        </a>
    </xsl:template>

    <!-- Zweiteilige Nebeneintragungen -->
    <xsl:template match="mabxml:feld[@nr &gt;='800']">
        <xsl:apply-templates select="mabxml:uf[@code='a']"/>
    </xsl:template>

    <!-- Nebeintragungsvermerk  -->
    <xsl:template name="nes">
        <authors>
            <xsl:for-each select="mabxml:feld[@nr='200' and @ind !=' ']|
  						mabxml:feld[@nr &gt;='204' and @nr &lt;='296']|
  						mabxml:feld[@nr='100' and @ind !=' ']|
              			mabxml:feld[@nr &gt;='104' and @nr &lt;='196']">
                <author>
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </author>
            </xsl:for-each>
        </authors>
        <xsl:for-each
                select="mabxml:feld[@nr='300']|
              mabxml:feld[@nr='304' and @ind='a']|
              mabxml:feld[@nr='310' and @ind='a']|
              mabxml:feld[@nr='331' and @ind !=' ']|
              mabxml:feld[@nr='335' and @ind='a']|
              mabxml:feld[@nr='340' and @ind='a']|
              mabxml:feld[@nr='341' and @ind='a']|
              mabxml:feld[@nr='344' and @ind='a']|
              mabxml:feld[@nr='345' and @ind='a']|
              mabxml:feld[@nr='370']|
              mabxml:feld[@nr &gt;='800' and @nr &lt;='829']">
            <xsl:choose>
                <xsl:when test="position()=1">
                    <br/>
                    <xsl:text>NE: </xsl:text>
                </xsl:when>
                <xsl:when test="(@nr &gt;=805) and (@nr &lt;=829) and (@nr mod 6 = 1)">
                    <xsl:text>: </xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>; </xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@nr='300'">
                    <xsl:apply-templates
                            select="../mabxml:feld[@nr='100']/mabxml:uf[@code='a']"/>
                    <xsl:text>: </xsl:text>
                    <xsl:apply-templates select="."/>
                </xsl:when>
                <xsl:when test="@nr='310'">
                    <xsl:text>AST</xsl:text>
                </xsl:when>
                <xsl:when test="@nr='331' and @ind='a'">
                    <xsl:text>HST</xsl:text>
                </xsl:when>
                <xsl:when test="@nr='331' and @ind='b'">
                    <xsl:apply-templates
                            select="../mabxml:feld[@nr='100']/mabxml:uf[@code='a']"/>
                    <xsl:text>: HST</xsl:text>
                </xsl:when>
                <xsl:when test="@nr='340' or @nr='341'">
                    <xsl:text>1. PT</xsl:text>
                </xsl:when>
                <xsl:when test="@nr='344' or @nr='345'">
                    <xsl:text>2. PT</xsl:text>
                </xsl:when>
                <xsl:when test="@nr='370' or @nr='335'">
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </xsl:when>
                <xsl:when test="@nr='304'">
                    <xsl:text>EST</xsl:text>
                    <xsl:if test="substring(mabxml:uf, string-length(mabxml:uf))='&gt;'">
                        <xsl:text> &lt;</xsl:text>
                        <xsl:value-of select="substring-after(mabxml:uf,'&lt;')"/>
                    </xsl:if>
                </xsl:when>
                <xsl:when test="@nr &gt;='100' and @nr &lt;='196'">
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                    <xsl:if test="@ind='a'">
                        <xsl:text>:</xsl:text>
                    </xsl:if>
                    <xsl:if test="@ind='f'">
                        <xsl:text>: Festschrift</xsl:text>
                    </xsl:if>
                    <xsl:if test="mabxml:uf[@code='b']">
                        <xsl:text> </xsl:text><xsl:value-of select="./mabxml:uf[@code='b']"/>
                    </xsl:if>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="mabxml:uf[@code='a']"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>
    </xsl:template>

    <!-- Bandangabe -->
    <xsl:template match="mabxml:feld[@nr='089']">
        <br/>
        <xsl:for-each select="mabxml:uf">
            <xsl:if test="position() !=1">
                <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:apply-templates select="."/>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="mabxml:uf[@code='a']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='b']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='c']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='d']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='e']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='p']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='u']">
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mabxml:uf[@code='s']">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template name="keywords">
        <xsl:for-each select="mabxml:feld[@nr ='902']|
  							   mabxml:feld[@nr ='907']|
  							   mabxml:feld[@nr ='912']|
  							   mabxml:feld[@nr ='917']|
  							   mabxml:feld[@nr ='922']|
  							   mabxml:feld[@nr ='927']|
  							   mabxml:feld[@nr ='932']|
  							   mabxml:feld[@nr ='937']|
  							   mabxml:feld[@nr ='942']|
  							   mabxml:feld[@nr ='947']">
            <keyword>
                <xsl:apply-templates select="mabxml:uf[@code='s']"/>
            </keyword>
        </xsl:for-each>

    </xsl:template>

</xsl:stylesheet>
