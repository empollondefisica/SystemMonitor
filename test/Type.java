
public class Type
{
    String Category;
    String Name;
    String Flags;
    String STPL;
    String Abbreviation;
    String ExternalName;
    String Format;
    String FormatString;
    String AlternateName;
    String Group;
    String UPD;
    String Territory;
    String DISP;
    String RVW;

    public Type()
    {
        Category = "";
        Name = "";
        Flags = "";
        STPL = "";
        Abbreviation = "";
        ExternalName = "";
        Format = "";
        FormatString = "";
        AlternateName = "";
        Group = "";
        UPD = "";
        Territory = "";
        DISP = "";
        RVW = "";
    }

    public String getCategory()
    {
        return Category;
    }

    public String getName()
    {
        return Name;
    }

    public String getFlags()
    {
        return Flags;
    }

    public String getSTPL()
    {
        return STPL;
    }

    public String getAbbreviation()
    {
        return Abbreviation;
    }

    public String getExternalName()
    {
        return ExternalName;
    }

    public String getFormat()
    {
        return Format;
    }

    public String getFormatString()
    {
        return FormatString;
    }

    public String getAlternateName()
    {
        return AlternateName;
    }

    public String getGroup()
    {
        return Group;
    }

    public String getUPD()
    {
        return UPD;
    }

    public String getTerritory()
    {
        return Territory;
    }

    public String getDISP()
    {
        return DISP;
    }

    public String getRVW()
    {
        return RVW;
    }

    public void parseRecord(String record)
    {
        StringBuilder buffer = new StringBuilder();
        boolean collect = false;
        String[] tokens = record.split( "\\s+" );

        Category = tokens[0];

        for( int i = 1; i < tokens.length; i++ )
        {
            if( tokens[i].contains( "\"" ) && !collect )
            {
                buffer.append( tokens[ i ] );
                collect = true;
            }
            else if( tokens[i].contains( "\"" ) && collect )
            {
                buffer.append( " " );
                buffer.append( tokens[i] );
                collect = false;
                assign( buffer.toString() );
                buffer.setLength( 0 );
            }
            else if( collect )
            {
                buffer.append( " " );
                buffer.append( tokens[i] );
            }
            else
            {
                buffer.append( tokens[i] );
                assign( buffer.toString() );
                buffer.setLength( 0 );
            }
        }
    }

    public void assign( String buffer )
    {
        String[] tokens = buffer.split( "=" );

        if( tokens[0].equals( "NM" ) )
        {
            Name = tokens[1];
        }
        else if( tokens[0].equals( "FL" ) )
        {
            if( tokens[1].length() > 2 )
            {
                Flags = tokens[1].substring(1, tokens[1].length() - 1);
            }
        }
        else if( tokens[0].equals( "STPL" ) )
        {
            STPL = tokens[1];
        }
        else if( tokens[0].equals( "ABBR" ) )
        {
            Abbreviation = tokens[1];
        }
        else if( tokens[0].equals( "EXT" ) )
        {
            ExternalName = tokens[1];
        }
        else if( tokens[0].equals( "FMT" ) )
        {
            Format = tokens[1];
        }
        else if( tokens[0].equals( "FMTSTR" ) )
        {
            FormatString = tokens[1];
        }
        else if( tokens[0].equals( "ALT" ) )
        {
            AlternateName = tokens[1];
        }
        else if( tokens[0].equals( "GRP" ) )
        {
            Group = tokens[1];
        }
        else if( tokens[0].equals( "DISP" ) )
        {
            DISP = tokens[1];
        }
        else if( tokens[0].equals( "RVW" ) )
        {
            RVW = tokens[1];
        }
        else if( tokens[0].equals( "UPD" ) )
        {
            UPD = tokens[1];
        }
        else if( tokens[0].equals( "TERR" ) )
        {
            Territory = tokens[1];
        }
        else
        {
            System.out.println( "TYPE " + Category + " -- Unknown name-value pair: " + buffer );
        }
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append( "TYPE" );
        buffer.append( "\n\t" ).append( Category );
        buffer.append( "\n\tNM: " ).append( Name );
        buffer.append( "\n\tFL: " ).append( Flags );
        buffer.append( "\n\tSTPL: " ).append( STPL );
        buffer.append( "\n\tABBR: " ).append( Abbreviation );
        buffer.append( "\n\tEXT: " ).append( ExternalName );
        buffer.append( "\n\tFMT: " ).append( Format );
        buffer.append( "\n\tFMTSTR: " ).append( FormatString );
        buffer.append( "\n\tALT: " ).append( AlternateName );
        return buffer.toString();
    }
}
