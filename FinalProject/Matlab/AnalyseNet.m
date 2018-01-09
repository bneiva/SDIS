%% Initialize variables.
addpath('C:\Users\Nuno Moreira\Documents\NetBeansProjects\Node\');  %Alterar aqui!

filename = 'Receivers.csv';
delimiter = ';';

%% Format string for each line of text:
%   column1: double (%f)
%	column2: double (%f)
%   column3: text (%s)
% For more information, see the TEXTSCAN documentation.
formatSpec = '%f%f%s%f%[^\n\r]';

%% Open the text file.
fileID = fopen(filename,'r');

%% Read columns of data according to format string.
% This call is based on the structure of the file used to generate this
% code. If an error occurs for a different file, try regenerating the code
% from the Import Tool.
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

%% Close the text file.
fclose(fileID);

%% Post processing for unimportable data.
% No unimportable data rules were applied during the import, so no post
% processing code is included. To generate code which works for
% unimportable data, select unimportable cells in a file and regenerate the
% script.

%% Allocate imported array to column variable names
Rx = dataArray{:, 1};
Tx = dataArray{:, 2};
Msg = dataArray{:, 3};
Level = dataArray{:,4};

VerifyTx=[];
VerifyRx=[];


for i=1:length(Tx)
    for k=1:length(Tx)
        if(Tx(k) == i-1)
            VerifyTx=[VerifyTx; Tx(k) Rx(k) Msg(k)];
        end
        if(Rx(k) == i-1)
            VerifyRx=[VerifyRx; Rx(k) Tx(k) Msg(k)];
        end
    end
end

xBins = 0:1:max(Rx)+1;
xVal = 0:1:max(Rx)+1;

figure(1)
h1 = hist(cell2mat(VerifyTx(:,1)),xBins);
hist(cell2mat(VerifyTx(:,1)),xBins)
title('Number of receivers of a given node');
axis([-1 100 0 inf]);
xlabel('Node id');


figure(2)
h2= hist(cell2mat(VerifyRx(:,1)),xBins);
hist(cell2mat(VerifyRx(:,1)),xBins)
title('Number of transmitters to a given node');
axis([-1 100 0 inf]);
xlabel('Node id');

disp('Número médio de Recetores de um dado nó');
meanRx = mean(h1)

disp('Número máximo de saltos');
maxHops = max(Level)

TxN = 0:1:max(h2)+1;
RxN = 0:1:max(h1)+1;

NrTxToNode = zeros(length(TxN)+1,1);
NrRxToNode = zeros(length(RxN)+1,1);

for i = 1:length(h2)-1
    for k = 1:max(TxN)
        if(TxN(k) == h2(i))
            NrTxToNode(k) = NrTxToNode(k)+1 ; 
        end
    end
end

NrTxToNode(1) = NrTxToNode(1)-1 ; 
    
figure(3);    
bar((0:1:length(NrTxToNode)-1),NrTxToNode)
title('Número de nós com um dado nº de transmiters');
xlabel('Número de transmissores');
axis([-0.5 max(TxN)+1 0 inf]);

for i = 1:length(h1)-1
    for k = 1:max(RxN)
        if(RxN(k) == h1(i))
            NrRxToNode(k) = NrRxToNode(k)+1 ;
        end
    end
end
    
NrRxToNode(1) = NrRxToNode(1)-1 ;

figure(4);
bar((0:1:length(NrRxToNode)-1),NrRxToNode)
title('Número de nós com um dado nº de receivers');
xlabel('Número de recetores');
axis([-0.5 max(RxN)+1 0 inf]);

%% Clear temporary variables
clearvars filename delimiter formatSpec fileID dataArray ans;